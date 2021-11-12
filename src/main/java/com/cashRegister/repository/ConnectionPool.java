package com.cashRegister.repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static ConnectionPool instance = null;

    private ConnectionPool(){
    }

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public static Connection getConnection(){
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            Context webContext = (Context)ctx.lookup("java:/comp/env");
            DataSource ds = (DataSource) webContext.lookup("jdbc/1");
            System.out.println("3434343");
            //DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/1");
            c = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
