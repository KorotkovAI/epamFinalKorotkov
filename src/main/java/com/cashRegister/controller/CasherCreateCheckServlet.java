package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.model.Check;
import com.cashRegister.model.Goods;
import com.cashRegister.model.Shift;
import com.cashRegister.model.User;
import com.cashRegister.repository.CheckGoodsRepository;
import com.cashRegister.repository.CheckRepository;
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
import java.util.List;

@WebServlet("/createCheck")
public class CasherCreateCheckServlet extends HttpServlet {

    private final GoodsRepository goodsRepository;
    private final CheckRepository checkRepository;
    private final CheckGoodsRepository checkGoodsRepository;

    private static final Logger log = LogManager.getLogger(CasherCreateCheckServlet.class);

    public CasherCreateCheckServlet() {
        goodsRepository = GoodsRepository.getGoodsRepository();
        checkRepository = CheckRepository.getCheckRepository();
        checkGoodsRepository = CheckGoodsRepository.getCheckGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCreatedCheck = 0;
        List<Goods> goodsCheck = (List<Goods>) req.getSession().getAttribute("goodsForCheck");
        if (goodsCheck != null) {
            boolean status = goodsRepository.deleteGoodsForCheck(goodsCheck);
            if (status) {
                Shift currentShift = (Shift) req.getSession().getAttribute("openshift");
                User currentUser = (User) req.getSession().getAttribute("user");
                idCreatedCheck = checkRepository.createCheck(goodsCheck, currentShift, currentUser);
                req.getSession().removeAttribute("goodsForCheck");
            } else {
                log.log(Level.ERROR, "into method come empty goodsCheck" + CasherCreateCheckServlet.class.getName());
                resp.sendRedirect(WebAdresses.ERROR_PAGE);
            }

            boolean addgoods = false;
            if (idCreatedCheck > 0) {
                addgoods = checkGoodsRepository.addCheckGoodsList(goodsCheck, idCreatedCheck);
            } else {
                log.log(Level.ERROR, "check did not created" + CasherCreateCheckServlet.class.getName());
                resp.sendRedirect(WebAdresses.ERROR_PAGE);
            }

            if (addgoods && status) {
                if (req.getSession().getAttribute("checksOfCasher") != null &&
                        req.getSession().getAttribute("user") != null) {
                    User currentUser = (User) req.getSession().getAttribute("user");
                    List<Check> checkList = checkRepository.getAllChecksCurrentCasherOpenShift(currentUser);
                    req.getSession().removeAttribute("checksOfCasher");
                    req.getSession().setAttribute("checksOfCasher", checkList);
                }
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.CASHER_START_PAGE);
                requestDispatcher.forward(req, resp);
            } else {
                log.log(Level.ERROR, "something going wrong with check or goods" + CasherCreateCheckServlet.class.getName());
                resp.sendRedirect(WebAdresses.ERROR_PAGE);
            }
        }
    }


}
