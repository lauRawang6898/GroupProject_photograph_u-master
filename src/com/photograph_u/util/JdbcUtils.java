package com.photograph_u.util;

import com.photograph_u.consts.JdbcConsts;

import java.sql.*;

public class JdbcUtils {
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    //
    /*
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");

    public static Connection getConnection() throws SQLException {
        Connection conn = connectionThreadLocal.get();
        if (conn == null) {
            conn = dataSource.getConnection();
        }
        return conn;
    }
    */
    //JDBC getconnection
    public static Connection getConnection() throws SQLException {
        Connection conn = connectionThreadLocal.get();
        if (conn == null) {
            try {
                Class.forName(JdbcConsts.DRIVER);
                conn = DriverManager.getConnection(JdbcConsts.URL, JdbcConsts.USERNAME, JdbcConsts.PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }


    public static PreparedStatement getPreparedStatement(Connection conn, String sql) throws SQLException {
        if (conn != null) {
            return conn.prepareStatement(sql);
        }
        return null;
    }

    public static void setParams(Object[] params, PreparedStatement pstm) throws SQLException {
        if (pstm != null && params != null) {
            for (int i = 1; i <= params.length; i++) {
                pstm.setObject(i, params[i - 1]);
            }
        }
    }

    public static void beginTransaction() {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            connectionThreadLocal.set(conn);
        } catch (SQLException e) {
            connectionThreadLocal.remove();
            close(conn, null, null);
            throw new RuntimeException(e);
        }

    }

    public static void commitTransaction() {
        Connection conn = connectionThreadLocal.get();
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException ex) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                throw new RuntimeException(ex);
            } finally {
                connectionThreadLocal.remove();
                close(conn, null, null);
            }
        }
    }

    public static void close(Connection conn, PreparedStatement pstm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (connectionThreadLocal.get() == null) {
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
