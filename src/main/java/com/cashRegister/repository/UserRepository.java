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

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DbManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("id");
                String userLogin = rs.getString("login");
                String userPassword = rs.getString("password");
                String userName = rs.getString("name");
                String userSurname = rs.getString("surname");
                Role userRoleName = roleRepository.getRoleByName(rs.getString("roleName"));
                users.add(new User(userId, userLogin, userPassword, userName, userSurname, userRoleName));
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public User getCurrentUser(int userId) {
        List<User> users = getAllUsers();
        return users.stream().filter(x -> x.getId() == userId).findFirst().orElse(null);
    }
}
