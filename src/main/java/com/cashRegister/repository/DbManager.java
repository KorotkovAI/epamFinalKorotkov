package com.cashRegister.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/cashregister";
    private static final String jdbcUser = "root";
    private static final String jdbcPassword = "PppSQL2501!";
    static Connection con = null;

    public static Connection getConnection() {
        if (con != null) {
            return con;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}

