package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.exception.GoodsNotFoundException;
import com.cashRegister.model.Goods;
import com.cashRegister.repository.GoodsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/checkAdd")
public class CheckAddCasherServlet extends HttpServlet {

    private GoodsRepository goodsRepository;
    private List<Goods> goodsForCheck = new ArrayList<>();

    private static final Logger log = LogManager.getLogger(ExpertAddGoodsServlet.class);

    public CheckAddCasherServlet() {
        goodsRepository = GoodsRepository.getGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Goods> goodsList = goodsRepository.getAllGoods();
        req.getSession().setAttribute("goodsList", goodsList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_CHECK_ADD);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPosName = null;
        int newPosAmount = 0;

        try {
            newPosName = req.getParameter("namePos");
            newPosAmount = Integer.parseInt(req.getParameter("amountPos"));
        } catch (Exception e) {
            System.out.println("problem");
            req.getSession().setAttribute("not availible params", "Can't use this mining");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_CHECK_ADD);
            requestDispatcher.forward(req, resp);
        }

        if (newPosAmount < 1 || newPosName == null || newPosName.isEmpty()) {
            req.getSession().setAttribute("not availible params", "Can't be empty or zero");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_CHECK_ADD);
            requestDispatcher.forward(req, resp);
        } else {
            Goods currentGoods = null;

            try {
                currentGoods = goodsRepository.getGoodsByName(newPosName);
            } catch (GoodsNotFoundException e) {
                e.printStackTrace();
            }

            if (newPosAmount > currentGoods.getAmount()) {
                req.getSession().setAttribute("not availible params", "There is not enough goods in the store");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_CHECK_ADD);
                requestDispatcher.forward(req, resp);
            } else {
                if (currentGoods != null) {
                    String name = currentGoods.getName();
                    if (goodsForCheck.stream().anyMatch(x -> x.getName().equals(name))) {
                       Goods goodsForUpdate = goodsForCheck.stream().filter(x -> x.getName().equals(name)).findFirst().get();
                       int newAmount = goodsForUpdate.getAmount() + newPosAmount;
                        if (newAmount > currentGoods.getAmount()) {
                            req.getSession().setAttribute("not availible params", "There is not enough goods in the store");
                            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_CHECK_RESULT);
                            requestDispatcher.forward(req, resp);
                        } else {
                            goodsForCheck.get(goodsForCheck.indexOf(goodsForUpdate)).setAmount(newAmount);
                        }
                    } else {
                        currentGoods.setAmount(newPosAmount);
                        goodsForCheck.add(currentGoods);
                    }
                }

                double totalPrice = 0.0;

                for (Goods goods : goodsForCheck) {
                    totalPrice += goods.getPrice() * goods.getAmount();
                }

                req.getSession().setAttribute("goodsForCheck", goodsForCheck);
                req.getSession().setAttribute("totalPrice", totalPrice);
                resp.sendRedirect(WebAdresses.CASHER_CHECK_RESULT_SERVLET);
            }
        }
    }
}
