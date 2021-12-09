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

@WebServlet("/deleteUser")
public class AdminDeleteUserServlet extends HttpServlet {

    private final UserRepository userRepository;

    private static final Logger log = LogManager.getLogger(AdminDeleteUserServlet.class);

    public AdminDeleteUserServlet() {
        userRepository = UserRepository.getUserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentUserId = Integer.parseInt(req.getParameter("id"));
        boolean isDeletedGoods = userRepository.deleteById(currentUserId);
        req.getSession().removeAttribute("usersList");
        List<User> usersList = userRepository.getAllUsers();
        req.getSession().setAttribute("usersList", usersList);
        RequestDispatcher requestDispatcher;
        if (isDeletedGoods) {
            resp.sendRedirect("/users");
        } else {
            requestDispatcher = req.getRequestDispatcher(WebAdresses.ERROR_PAGE);
            requestDispatcher.forward(req, resp);
        }
    }
}
