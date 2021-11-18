package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.model.Role;
import com.cashRegister.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String INSERT_USER = "INSERT INTO users" +
            " (login, password, name, surname, roleName) VALUES " + " (?, ?, ?);";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";

    private RoleRepository roleRepository;
    private static UserRepository userRepository = null;

    public synchronized static UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public UserRepository() {
        roleRepository = RoleRepository.getRoleRepository();
    }

    public void addUser(User user) {
        try (Connection con = DbManager.getConnection()) {
            //PreparedStatement preparedStatement = con.prepareStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            Connection connection = DbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("id");
                String userLogin = rs.getString("login");
                String userPassword = rs.getString("password");
                //System.out.println(userLogin + userPassword);
                String userName = rs.getString("name");
                String userSurname = rs.getString("surname");
                Role userRoleName = roleRepository.getRoleByName(rs.getString("roleName"));
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
