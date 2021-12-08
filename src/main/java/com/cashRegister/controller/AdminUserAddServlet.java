package com.cashRegister.controller;

import com.cashRegister.WebAdresses;
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

@WebServlet("/userAdd")
public class AdminUserAddServlet extends HttpServlet {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private static final Logger log = LogManager.getLogger(AdminUserAddServlet.class);

    public AdminUserAddServlet() {
        userRepository = UserRepository.getUserRepository();
        roleRepository = RoleRepository.getRoleRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> roles = roleRepository.getAllRoles();
        req.getSession().setAttribute("roles", roles);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_ADD);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newUserLogin = null;
        String newUserName = null;
        String newUserSurname = null;
        String newUserPassword = null;
        Role newUserRole = null;
        String roleName = null;
        boolean flag = false;

        List<User> users = userRepository.getAllUsers();

        if (req.getSession().getAttribute("not save user") != null) {
            req.getSession().removeAttribute("not save user");
        }

        try {
            newUserLogin = req.getParameter("login").trim();
            newUserName = req.getParameter("name").trim();
            newUserSurname = req.getParameter("surname").trim();
            newUserPassword = req.getParameter("password").trim();
            roleName = req.getParameter("role").trim();
        } catch (Exception e) {
            log.log(Level.ERROR, e);
            req.getSession().setAttribute("not save user", "Sorry some problems with saving");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_ADD);
            requestDispatcher.forward(req, resp);
            flag = true;
        }

        if (newUserLogin == null || newUserName == null || newUserSurname == null || newUserPassword == null || roleName == null) {
            req.getSession().setAttribute("not save user", "Can't be null");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_ADD);
            requestDispatcher.forward(req, resp);
            flag = true;
        } else if (newUserLogin.isEmpty() || newUserName.isEmpty() || newUserSurname.isEmpty() || newUserPassword.isEmpty() || roleName.isEmpty()) {
            req.getSession().setAttribute("not save user", "Can't be empty");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_ADD);
            requestDispatcher.forward(req, resp);
            flag = true;
        } else if (!newUserName.matches("[A-Za-z].{2,}") || !newUserSurname.matches("[A-Za-z].{2,}")) {
            req.getSession().setAttribute("not save user", "Can't use cyrillic and numbers in name and surname and minimum 2 letters");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_ADD);
            requestDispatcher.forward(req, resp);
            flag = true;
        } else if (!newUserPassword.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}")) {
            System.out.println("154");
            req.getSession().setAttribute("not save user", "In password must be at least one number, " +
                    "lower case letter and upper case letter and minimum 8 chars long");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_ADD);
            requestDispatcher.forward(req, resp);
            flag = true;
        } else if (!newUserLogin.matches("[A-Za-z].{3,}")) {
            req.getSession().setAttribute("not save user", "Login can contain only latin letters " +
                    "and must be at least 3 symbols");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_ADD);
            requestDispatcher.forward(req, resp);
            flag = true;
        }

        for (User user : users) {
            if (user.getLogin().equalsIgnoreCase(newUserLogin)) {
                req.getSession().setAttribute("not save user", "Can't use this login");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_ADD);
                requestDispatcher.forward(req, resp);
                flag = true;
            }
        }

        for (Role role : roleRepository.getAllRoles()) {
            if (role.getName().equals(roleName)) {
                newUserRole = role;
            }
        }

        if (newUserRole == null || flag) {
            req.getSession().setAttribute("not save user", "Some problems with repository");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_ADD);
            requestDispatcher.forward(req, resp);
        } else {
            User newUser = new User(newUserLogin, newUserPassword, newUserName, newUserSurname, newUserRole);
            boolean isAdd = userRepository.addUser(newUser);

            if (isAdd) {
                resp.sendRedirect("/users");
            } else {
                req.getSession().setAttribute("not save user", "Some problems with DB");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(WebAdresses.USER_ADD);
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
