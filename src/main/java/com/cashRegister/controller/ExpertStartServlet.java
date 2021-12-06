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
    private final GoodsRepository goodsRepository;

    private static final Logger log = LogManager.getLogger(ExpertStartServlet.class);

    public ExpertStartServlet() {
        goodsRepository = GoodsRepository.getGoodsRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("111111");
        System.out.println(req.getParameter("page"));
        req.getSession().removeAttribute("goodsList");
        int page = 1;
        int recordsPerPage = 5;
        if(req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        System.out.println("current page" + page);
        List<Goods> goodsList = goodsRepository.getAllGoods();
        int limitPos;
        if (((page-1)*recordsPerPage + recordsPerPage) > goodsList.size()) {
            limitPos = goodsList.size();
        } else {
            limitPos = ((page-1)*recordsPerPage + recordsPerPage);
        }
        List<Goods> result = goodsList.subList((page-1)*recordsPerPage, limitPos);
        result.stream().map(x -> x.getName()).forEach(System.out::print);
        int noOfRecords = goodsList.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        LocalDate localDate = LocalDate.now();
        req.getSession().setAttribute("noOfPages", noOfPages);
        req.getSession().setAttribute("currentPage", page);
        req.getSession().setAttribute("localDate", localDate);
        req.getSession().setAttribute("goodsList", result);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.EXPERT_START_PAGE);
        requestDispatcher.forward(req, resp);
    }
}
