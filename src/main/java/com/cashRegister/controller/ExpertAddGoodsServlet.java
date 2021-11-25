package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
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

@WebServlet("/goodsAdd")
public class ExpertAddGoodsServlet extends HttpServlet {

    private final GoodsRepository goodsRepository;

    private static final Logger log = LogManager.getLogger(ExpertAddGoodsServlet.class);

    public ExpertAddGoodsServlet() {
        goodsRepository = GoodsRepository.getGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPERT_GOODS_ADD);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newGoodsName = null;
        int newGoodsAmount = -1;
        double newGoodsPrice = -2.0;

        try {
            newGoodsName = req.getParameter("nameGoods");
            newGoodsAmount = Integer.parseInt(req.getParameter("amountGoods"));
            newGoodsPrice = Double.parseDouble(req.getParameter("priceGoods"));
        } catch (Exception e) {
            log.log(Level.ERROR, e);
            req.getSession().setAttribute("not save goods", "Can't use this mining");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPERT_GOODS_ADD);
            requestDispatcher.forward(req, resp);
        }

        if (newGoodsAmount < 0 || newGoodsName == null || newGoodsPrice < 0.0) {
            req.getSession().setAttribute("not save goods", "Can't be empty or negative");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPERT_GOODS_ADD);
            requestDispatcher.forward(req, resp);
        }

        Goods goods = new Goods(newGoodsName, newGoodsAmount, newGoodsPrice);
        boolean isAdd = goodsRepository.addGoods(goods);

        if (isAdd) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/expertStart");
            requestDispatcher.forward(req, resp);
        } else {
            req.getSession().setAttribute("not save goods", "Can't use this name");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPERT_GOODS_ADD);
            requestDispatcher.forward(req, resp);
        }
    }
}
