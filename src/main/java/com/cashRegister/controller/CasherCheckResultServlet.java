package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class is responsible for view result check
 */
@WebServlet(WebAdresses.CASHER_CHECK_RESULT_SERVLET)
public class CasherCheckResultServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(CasherCheckResultServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_CHECK_RESULT);
        requestDispatcher.forward(req, resp);
    }

}
