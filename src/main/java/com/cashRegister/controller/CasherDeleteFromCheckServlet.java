package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.model.Goods;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebAdresses.CASHER_DELETE_GOODS_FROM_CHECK)
public class CasherDeleteFromCheckServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(CasherDeleteFromCheckServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int currentGoodsId = Integer.parseInt(req.getParameter("id"));
            List<Goods> goodsInCheck = (List<Goods>) req.getSession().getAttribute("goodsForCheck");
            goodsInCheck.removeIf(x -> x.getId() == currentGoodsId);

            if (goodsInCheck.isEmpty()) {
                resp.sendRedirect(WebAdresses.CASHER_START_PAGE);
            } else {

                double totalPrice = 0.0;

                for (Goods goods : goodsInCheck) {
                    totalPrice += goods.getPrice() * goods.getAmount();
                }

                req.getSession().removeAttribute("totalPrice");
                req.getSession().setAttribute("totalPrice", totalPrice);
                req.getSession().removeAttribute("goodsForCheck");
                req.getSession().setAttribute("goodsForCheck", goodsInCheck);
                resp.sendRedirect(WebAdresses.CASHER_CHECK_RESULT_SERVLET);
            }
        } catch (NumberFormatException e) {
            log.log(Level.ERROR, e.getMessage() + CasherDeleteFromCheckServlet.class.getName());
        }
    }
}
