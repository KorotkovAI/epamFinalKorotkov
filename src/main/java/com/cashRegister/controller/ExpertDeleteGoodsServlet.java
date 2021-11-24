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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/deleteGoods")
public class ExpertDeleteGoodsServlet extends HttpServlet {

    private GoodsRepository goodsRepository;

    private static final Logger log = LogManager.getLogger(ExpertDeleteGoodsServlet.class);

    public ExpertDeleteGoodsServlet() {
        this.goodsRepository = GoodsRepository.getGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String currentGoodsName = req.getParameter("goods");
            boolean isDeletedGoods = goodsRepository.deleteByName(currentGoodsName);
            req.getSession().removeAttribute("goodsList");
            List<Goods> goodsList = goodsRepository.getAllGoods();
            req.getSession().setAttribute("goodsList", goodsList);

            if (isDeletedGoods) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/expertStart");
                requestDispatcher.forward(req, resp);
            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.ERROR_PAGE);
                requestDispatcher.forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
