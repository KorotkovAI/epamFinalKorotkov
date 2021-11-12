package com.cashRegister.controller;

import com.cashRegister.model.Role;
import com.cashRegister.model.User;
import com.cashRegister.model.WebAdresses;
import com.cashRegister.repository.DbManager;
import com.cashRegister.repository.RoleRepository;
import com.cashRegister.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

@WebServlet(value = {"/", "/home"})
public class HomeServlet extends HttpServlet {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public HomeServlet() {
        this.userRepository = new UserRepository();
        this.roleRepository = new RoleRepository();
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
        Connection con = DbManager.getConnection();

        String currentRole;
        String forwardPage = null;
        RequestDispatcher requestDispatcher;
        for (User user : userRepository.getAllUsers(con)) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                currentRole = user.getRoleName().getName();
                try {
                    if (currentRole.equals("Admin")) {
                        forwardPage = WebAdresses.ADMIN_START_PAGE;
                    } else if (currentRole.equals("Casher")) {
                        forwardPage = WebAdresses.CASHER_START_PAGE;
                    } else if (currentRole.equals("CommodityExpert")) {
                        forwardPage = WebAdresses.EXPER_START_PAGE;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.setAttribute("user", user);
                requestDispatcher = req.getRequestDispatcher(forwardPage);
                requestDispatcher.forward(req, resp);
            }
        }


    }
/*
    private void process (HttpServletRequest req, HttpServletResponse resp) {

        try {
            DataSource ds = null;
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/TestDB");
            Connection con = ds.getConnection();

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * FROM users;");
            while (resultSet.next()) {
                String temp = resultSet.getString("roleName");
                System.out.println(temp);
            }
            webadr = "/WEB-INF/pages/casherStart.jsp";
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

 */
}
