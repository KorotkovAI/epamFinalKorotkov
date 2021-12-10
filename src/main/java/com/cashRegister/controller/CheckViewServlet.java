package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.model.Check;
import com.cashRegister.model.Goods;
import com.cashRegister.repository.CheckGoodsRepository;
import com.cashRegister.repository.CheckRepository;
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
import java.util.List;

@WebServlet("/checkView")
public class CheckViewServlet extends HttpServlet {

    private final CheckRepository checkRepository;
    private final CheckGoodsRepository checkGoodsRepository;

    private static final Logger log = LogManager.getLogger(CheckViewServlet.class);

    public CheckViewServlet() {
        checkRepository = CheckRepository.getCheckRepository();
        checkGoodsRepository = CheckGoodsRepository.getCheckGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            int idCurrentCheck = Integer.parseInt(req.getParameter("id"));
            Check currentCheck = checkRepository.getCurrentCheck(idCurrentCheck);

            if (currentCheck != null) {
                List<Goods> goodsList = checkGoodsRepository.returnCheckGoods(idCurrentCheck);
                req.getSession().setAttribute("goods", goodsList);
                req.getSession().setAttribute("check", currentCheck);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CHECK_PAGE);
                requestDispatcher.forward(req, resp);
            } else {
                log.log(Level.ERROR, "did not find check by id" + CheckViewServlet.class.getName());
                resp.sendRedirect(WebAdresses.ERROR_PAGE);
            }
        } else {
            log.log(Level.ERROR, "did not get param id" + CheckViewServlet.class.getName());
            resp.sendRedirect(WebAdresses.ERROR_PAGE);
        }
    }
}
