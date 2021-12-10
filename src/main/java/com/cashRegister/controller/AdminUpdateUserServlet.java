package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
import com.cashRegister.exception.RoleNotFoundException;
import com.cashRegister.model.Role;
import com.cashRegister.model.User;
import com.cashRegister.repository.RoleRepository;
import com.cashRegister.repository.UserRepository;
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

/**
 * The class is responsible for update Users
 */
@WebServlet("/userUpdate")
public class AdminUpdateUserServlet extends HttpServlet {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private static final Logger log = LogManager.getLogger(AdminUpdateUserServlet.class);

    public AdminUpdateUserServlet() {
        userRepository = UserRepository.getUserRepository();
        roleRepository = RoleRepository.getRoleRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        User user = userRepository.getCurrentUser(userId);
        req.getSession().setAttribute("userForUpdate", user);
        List<Role> roles = roleRepository.getAllRoles();
        req.getSession().setAttribute("roles", roles);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_UPDATE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = 0;
        String login = null;
        String name = null;
        String surname = null;
        String password = null;
        String role = null;

        try {
            userId = Integer.parseInt(req.getParameter("idUser"));
            login = req.getParameter("loginUser");
            name = req.getParameter("nameUser");
            surname = req.getParameter("surnameUser");
            password = req.getParameter("passUser");
            role = req.getParameter("role");
        } catch (NumberFormatException e) {
            log.log(Level.ERROR, e.getMessage() + AdminUpdateUserServlet.class.getName());
            req.getSession().setAttribute("wrongMining", "Sorry you use not correct value");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_UPDATE);
            requestDispatcher.forward(req, resp);
        }

        if (userId < 1 || login == null || name == null || surname == null || password == null || role == null) {
            req.getSession().setAttribute("wrongMining", "Sorry not enough parametr");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_UPDATE);
            requestDispatcher.forward(req, resp);
        } else {
            Role roleUser = null;
            try {
                roleUser = roleRepository.getRoleByName(role);
            } catch (RoleNotFoundException e) {
                log.log(Level.ERROR, e.getMessage() + AdminTodayCloseServlet.class.getName());
            }
            User newUser = new User(userId, login, password, name, surname, roleUser);

            try {
                boolean updateComplite = userRepository.update(newUser);
                if (updateComplite) {
                    resp.sendRedirect("/users");
                } else {
                    req.getSession().setAttribute("notFoundUser", "Sorry user not updated");
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_UPDATE);
                    requestDispatcher.forward(req, resp);
                }
            } catch (Exception e) {
                log.log(Level.ERROR, e.getMessage() + AdminTodayCloseServlet.class.getName());
            }
        }
    }
}
