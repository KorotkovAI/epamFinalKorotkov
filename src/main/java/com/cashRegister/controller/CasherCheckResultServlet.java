package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.model.Goods;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebAdresses.CASHER_CHECK_RESULT_SERVLET)
public class CasherCheckResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("sssssssssss");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_CHECK_RESULT);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        System.out.println("pppppppppp");
        List<Goods> checkGoods = (List<Goods>) req.getSession().getAttribute("goodsForCheck");

        for (Goods goods: checkGoods) {
            System.out.println(goods.getName() + " " + goods.getAmount());
        }
    }
}