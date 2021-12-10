package com.cashRegister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class create connection with DB
 */
public class DbManager {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/cashregister";
    private static final String jdbcUser = "root";
    private static final String jdbcPassword = "PppSQL2501!";
    private static DbManager dbManager;

    public static synchronized DbManager getInstance() {
        if (dbManager == null) {
            dbManager = new DbManager();
        }
        return dbManager;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}

