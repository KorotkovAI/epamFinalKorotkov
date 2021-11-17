package com.cashRegister.controller;

import com.cashRegister.model.User;
import com.cashRegister.WebAdresses;
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
import java.io.*;

@WebServlet(value = {"/", "/home"})
public class HomeServlet extends HttpServlet {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private static final Logger log = LogManager.getLogger(HomeServlet.class);

    public HomeServlet() {
        this.userRepository = UserRepository.getUserRepository();
        this.roleRepository = RoleRepository.getRoleRepository();
    }

    //WEB-INF/pages/home.jsp
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.HOME_PAGE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        log.debug("get login " + login + " password" + password);

        String currentRole;
        String forwardPage = WebAdresses.ERROR_PAGE;
        RequestDispatcher requestDispatcher;
        for (User user : userRepository.getAllUsers()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                currentRole = user.getRoleName().getName();
                try {
                    if (currentRole.equals("Admin")) {
                        forwardPage = WebAdresses.ADMIN_START_PAGE;
                    } else if (currentRole.equals("Casher")) {
                        forwardPage = WebAdresses.CASHER_START_PAGE;
                    } else if (currentRole.equals("CommodityExpert")) {
                        forwardPage = "/expertStart";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getSession().setAttribute("user", user);
                requestDispatcher = req.getRequestDispatcher(forwardPage);
                requestDispatcher.forward(req, resp);
            }
        }
        req.setAttribute("not found user", "User with such login and password not found");
        requestDispatcher = req.getRequestDispatcher(WebAdresses.HOME_PAGE);
        requestDispatcher.forward(req, resp);
    }
}
