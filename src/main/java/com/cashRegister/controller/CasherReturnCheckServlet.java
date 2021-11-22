package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.exception.CheckReturnedException;
import com.cashRegister.model.Goods;
import com.cashRegister.repository.CheckGoodsRepository;
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

@WebServlet(WebAdresses.CASHER_RETURN_CHECK)
public class CasherReturnCheckServlet extends HttpServlet {

    private CheckRepository checkRepository;
    private CheckGoodsRepository checkGoodsRepository;
    private GoodsRepository goodsRepository;

    private static final Logger log = LogManager.getLogger(CasherReturnCheckServlet.class);

    public CasherReturnCheckServlet() {
        this.checkRepository = CheckRepository.getCheckRepository();
        this.checkGoodsRepository = CheckGoodsRepository.getCheckGoodsRepository();
        this.goodsRepository = GoodsRepository.getGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            int idCurrentCheck = Integer.parseInt(req.getParameter("id"));
            try {
                boolean status = checkRepository.checkReturn(idCurrentCheck);
                if (status) {
                    List<Goods> goodsForReturn = checkGoodsRepository.returnCheckGoods(idCurrentCheck);
                    System.out.println("goods for return");
                    boolean res = goodsRepository.returnGoods(goodsForReturn);
                    if (res) {
                        req.getSession().setAttribute("check returned", "check successfully returned back money for client");
                        resp.sendRedirect(WebAdresses.CASHER_START_PAGE);
                    }
                }
            } catch (CheckReturnedException e) {
                req.getSession().setAttribute("check returned", e.getMessage());
                resp.sendRedirect(WebAdresses.CASHER_START_PAGE);
            }
        } else {
            resp.sendRedirect(WebAdresses.ERROR_PAGE);
        }
    }
}
