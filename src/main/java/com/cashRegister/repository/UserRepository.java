package com.cashRegister.repository;

import com.cashRegister.controller.HomeServlet;
import com.cashRegister.model.Role;
import com.cashRegister.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/cashregister";
    private static final String jdbcUser = "root";
    private static final String jdbcPassword = "PppSQL2501!";

    private static final String INSERT_USER = "INSERT INTO users" +
            " (login, password, name, surname, roleName) VALUES " + " (?, ?, ?);";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";

    private RoleRepository roleRepository;

    public UserRepository() {
        this.roleRepository = new RoleRepository();
    }

    /*
    Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

 */

    public void addUser(User user) {
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
            //PreparedStatement preparedStatement = con.prepareStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<User> getAllUsers(Connection con) {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("id");
                String userLogin = rs.getString("login");
                String userPassword = rs.getString("password");
                String userName = rs.getString("name");
                String userSurname = rs.getString("surname");
                Role userRoleName = roleRepository.getRoleByName(con, rs.getString("roleName"));
                users.add(new User(userId, userLogin, userPassword, userName, userSurname, userRoleName));
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

}
