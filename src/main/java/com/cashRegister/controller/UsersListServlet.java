package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.model.User;
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

/**
 * The class is responsible for view list of users
 */
@WebServlet("/users")
public class UsersListServlet extends HttpServlet {
    private final UserRepository userRepository;

    private static final Logger log = LogManager.getLogger(UsersListServlet.class);

    public UsersListServlet() {
        userRepository = UserRepository.getUserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("usersList");
        int page = 1;
        int recordsPerPage = 5;
        if(req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<User> userList = userRepository.getAllUsers();
        int limitPos;
        if (((page-1)*recordsPerPage + recordsPerPage) > userList.size()) {
            limitPos = userList.size();
        } else {
            limitPos = ((page-1)*recordsPerPage + recordsPerPage);
        }
        List<User> result = userList.subList((page-1)*recordsPerPage, limitPos);
        int noOfRecords = userList.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.getSession().setAttribute("noOfPages", noOfPages);
        req.getSession().setAttribute("currentPage", page);
        req.getSession().setAttribute("usersList", result);
        if (req.getSession().getAttribute("not save user") != null) {
            req.getSession().removeAttribute("not save user");
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USERS_LIST);
        requestDispatcher.forward(req, resp);
    }

}
