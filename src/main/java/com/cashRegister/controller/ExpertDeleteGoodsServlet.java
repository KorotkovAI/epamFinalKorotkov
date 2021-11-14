package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
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

@WebServlet("/deleteGoods")
public class ExpertDeleteGoodsServlet extends HttpServlet {

    private GoodsRepository goodsRepository;

    private static final Logger log = LogManager.getLogger(ExpertDeleteGoodsServlet.class);

    public ExpertDeleteGoodsServlet() {
        this.goodsRepository = GoodsRepository.getGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("-----------");
        try {
            int goodsId = Integer.parseInt(req.getParameter("id"));
            System.out.println(goodsId);
            boolean isDeletedGoods = goodsRepository.deleteById(goodsId);;
            System.out.println(isDeletedGoods);
            if (isDeletedGoods) {
                System.out.println("hhhhhhhhhhh");
                //TODO cant refresh list of goods after deleting
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ttt");
                requestDispatcher.forward(req, resp);
            } else {
                System.out.println("I cant see this");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ttt");
                requestDispatcher.forward(req, resp);
                System.out.println("too match");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("dopost");
    }
}
