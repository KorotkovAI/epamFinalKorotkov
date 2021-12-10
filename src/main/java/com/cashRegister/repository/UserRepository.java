package com.cashRegister.repository;

import com.cashRegister.DbManager;
import com.cashRegister.exception.GoodsNotFoundException;
import com.cashRegister.model.Goods;
import com.cashRegister.model.Role;
import com.cashRegister.model.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class works with users
 */
public class UserRepository {

    private static final String INSERT_USER = "INSERT INTO users" +
            " (login, password, name, surname, roleName) VALUES " + " (?, ?, ?, ?, ?);";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USER = "UPDATE users SET login = ?, password = ?, name = ?, surname = ?, roleName = ? WHERE id = ?;";

    private static final Logger log = LogManager.getLogger(UserRepository.class);

    private final RoleRepository roleRepository;
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
        } catch (Exception e) {
            log.log(Level.ERROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
            }
        }
        return users;
    }

    public User getCurrentUser(int userId) {
        List<User> users = getAllUsers();
        return users.stream().filter(x -> x.getId() == userId).findFirst().orElse(null);
    }

    public boolean addUser(User user) {
        if (user != null) {
            List<User> userList = getAllUsers();
            boolean status = userList.stream().anyMatch(x -> x.getLogin().equals(user.getLogin()));

            if (!status) {
                Connection connection = null;
                PreparedStatement preparedStatement = null;

                try {
                    connection = DbManager.getInstance().getConnection();
                    preparedStatement = connection.prepareStatement(INSERT_USER);
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getName());
                    preparedStatement.setString(4, user.getSurname());
                    preparedStatement.setString(5, user.getRoleName().getName());
                    preparedStatement.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    log.log(Level.ERROR, e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        log.log(Level.ERROR, e);
                    }
                }
            }
        }
        return false;
    }

    public boolean update(User user) {
        List<User> userList = getAllUsers();
        User updateUser = userList.stream().filter(x -> x.getId() == user.getId()).findFirst().orElse(null);

        if (updateUser != null) {
            if (updateUser.equals(user)) {
                log.log(Level.ERROR, "equals users");
                return false;
            }
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = DbManager.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(UPDATE_USER);
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getName());
                preparedStatement.setString(4, user.getSurname());
                preparedStatement.setString(5, user.getRoleName().getName());
                preparedStatement.setInt(6, user.getId());
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.log(Level.ERROR, e);
                }
            }
        }
        return false;
    }

    public boolean deleteById(int id) {
        List<User> userList = userRepository.getAllUsers();
        User oldUser = userList.stream().filter(x -> x.getId() == id).findFirst().get();

        if (oldUser != null) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = DbManager.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(DELETE_USER);
                preparedStatement.setInt(1, oldUser.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                log.log(Level.ERROR, e);
            }
            return true;
        }
        return false;
    }
}
