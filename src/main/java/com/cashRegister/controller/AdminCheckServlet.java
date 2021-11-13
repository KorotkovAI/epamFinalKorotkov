package com.cashRegister.controller;

import com.cashRegister.WebAdresses;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/adminChek")
public class AdminCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("11111111111");
        Enumeration<String> ttt = req.getSession().getAttributeNames();
        while (ttt.hasMoreElements()) {
            System.out.println(ttt.nextElement());
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_START_PAGE);
        requestDispatcher.forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("22222222222");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_START_PAGE);
        requestDispatcher.forward(req, resp);
    }
}
