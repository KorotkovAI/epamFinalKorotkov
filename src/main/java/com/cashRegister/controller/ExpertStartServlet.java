package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.model.Goods;
import com.cashRegister.repository.GoodsRepository;
import com.cashRegister.repository.RoleRepository;
import com.cashRegister.repository.UserRepository;
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

@WebServlet("/ttt")
public class ExpertStartServlet extends HttpServlet {
    private GoodsRepository goodsRepository;

    private static final Logger log = LogManager.getLogger(ExpertStartServlet.class);

    public ExpertStartServlet() {
        this.goodsRepository = GoodsRepository.getGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("777777");
        //RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPER_START_PAGE);
        //requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("888888888");
        List<Goods> goodsList = goodsRepository.getAllGoods();
        for (Goods goods: goodsRepository.getAllGoods()) {
            System.out.println(goods.getName());
        }
        req.getSession().setAttribute("goodsList", goodsList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPER_START_PAGE);
        requestDispatcher.forward(req, resp);
    }
}
