package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.model.Goods;
import com.cashRegister.model.Shift;
import com.cashRegister.model.User;
import com.cashRegister.repository.CheckRepository;
import com.cashRegister.repository.GoodsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/createCheck")
public class CasherCreateCheckServlet extends HttpServlet {

    private GoodsRepository goodsRepository;
    private CheckRepository checkRepository;

    private static final Logger log = LogManager.getLogger(CasherCreateCheckServlet.class);

    public CasherCreateCheckServlet() {
        this.goodsRepository = GoodsRepository.getGoodsRepository();
        this.checkRepository = CheckRepository.getCheckRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean statusCreateCheck = false;
        List<Goods> goodsCheck = (List<Goods>) req.getSession().getAttribute("goodsForCheck");
        if (goodsCheck != null) {
            boolean status = goodsRepository.deleteGoodsForCheck(goodsCheck);
            if (status) {
                Shift currentShift = (Shift) req.getSession().getAttribute("openshift");
                User currentUser = (User) req.getSession().getAttribute("user");
                statusCreateCheck = checkRepository.createCheck(goodsCheck, currentShift, currentUser);
                req.getSession().removeAttribute("goodsForCheck");
            } else {
                resp.sendRedirect(WebAdresses.ERROR_PAGE);
            }

            if (statusCreateCheck) {

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ccccccccccc");
    }
}
