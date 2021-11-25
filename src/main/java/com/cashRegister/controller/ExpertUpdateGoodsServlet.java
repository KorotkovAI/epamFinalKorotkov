package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.exception.GoodsNotFoundException;
import com.cashRegister.model.Goods;
import com.cashRegister.repository.GoodsRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goodsUpdate")
public class ExpertUpdateGoodsServlet extends HttpServlet {

    private final GoodsRepository goodsRepository;
    private Goods goods;

    private static final Logger log = LogManager.getLogger(ExpertUpdateGoodsServlet.class);

    public ExpertUpdateGoodsServlet() {
        goodsRepository = GoodsRepository.getGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int goodsId = Integer.parseInt(req.getParameter("id"));
            goods = GoodsRepository.getGoodsRepository().getGoodsById(goodsId);
        } catch (GoodsNotFoundException e) {
            log.log(Level.ERROR, e);
        }
        req.getSession().setAttribute("goodsForUpdate", goods);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPERT_GOODS_UPDATE_PAGE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int newGoodsId = 0;
        String newGoodsName = null;
        int newGoodsAmount = -1;
        double newGoodsPrice = -1.0;

        try {
            newGoodsId = Integer.parseInt(req.getParameter("idGoods"));
            newGoodsName = req.getParameter("nameGoods");
            newGoodsAmount = Integer.parseInt(req.getParameter("amountGoods"));
            newGoodsPrice = Double.parseDouble(req.getParameter("priceGoods"));
        } catch (NumberFormatException e) {
            log.log(Level.ERROR, e);
            req.getSession().setAttribute("wrongMining", "Sorry you use not correct value");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPERT_GOODS_UPDATE_PAGE);
            requestDispatcher.forward(req, resp);
        }

        if (newGoodsId < 1 || newGoodsName == null || newGoodsName.isEmpty() || newGoodsAmount < 0 || newGoodsPrice < 0.0) {
            req.getSession().setAttribute("wrongMining", "Sorry you use not correct value");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPERT_GOODS_UPDATE_PAGE);
            requestDispatcher.forward(req, resp);
        } else {

            Goods newGoods = new Goods(newGoodsId, newGoodsName, newGoodsAmount, newGoodsPrice);

            try {
                boolean updateComplite = goodsRepository.update(newGoods);
                if (updateComplite) {
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/expertStart");
                    requestDispatcher.forward(req, resp);
                } else {
                    req.getSession().setAttribute("notFoundGoods", "Sorry something wrong with goods");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPERT_GOODS_UPDATE_PAGE);
                    requestDispatcher.forward(req, resp);
                }
            } catch (GoodsNotFoundException e) {
                log.log(Level.ERROR, e);
            }
        }
    }
}
