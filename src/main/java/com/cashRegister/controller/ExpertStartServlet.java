package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.model.Goods;
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
import java.time.LocalDate;
import java.util.List;

@WebServlet("/expertStart")
public class ExpertStartServlet extends HttpServlet {
    private GoodsRepository goodsRepository;

    private static final Logger log = LogManager.getLogger(ExpertStartServlet.class);

    public ExpertStartServlet() {
        this.goodsRepository = GoodsRepository.getGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPERT_START_PAGE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("goodsList");
        List<Goods> goodsList = null;
        goodsList = goodsRepository.getAllGoods();
        LocalDate localDate = LocalDate.now();
        req.getSession().setAttribute("localDate", localDate);
        req.getSession().setAttribute("goodsList", goodsList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPERT_START_PAGE);
        requestDispatcher.forward(req, resp);
    }
}
