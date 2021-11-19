package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.model.Goods;
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

    private static final Logger log = LogManager.getLogger(CasherCreateCheckServlet.class);

    public CasherCreateCheckServlet() {
        this.goodsRepository = GoodsRepository.getGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("bbbbbbbbbbb");
        List<Goods> goodsCheck = (List<Goods>) req.getSession().getAttribute("goodsForCheck");
        if (goodsCheck != null) {
            goodsCheck.stream().map(x -> x.getName()).forEach(System.out::print);
            System.out.println("----------");
            boolean status = goodsRepository.deleteGoodsForCheck(goodsCheck);
            System.out.println(status + " super");
            req.getSession().removeAttribute("goodsForCheck");
            System.out.println("delete from check");
            List<Goods> newFFFF = (List<Goods>) req.getSession().getAttribute("goodsForCheck");
            System.out.println("cant see next");
            if (newFFFF != null) {
                newFFFF.stream().map(x -> x.getName()).forEach(System.out::print);
            }
        }
        resp.sendRedirect(WebAdresses.ERROR_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ccccccccccc");
    }
}
