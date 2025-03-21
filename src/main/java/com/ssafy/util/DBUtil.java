package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Mysql DB 연결 객체를 제공해주고, 사용했던 자원을 해제하는 기능을 제공하는 클래스입니다.
 */
public class DBUtil {

    // DB Config
    private final String url = "jdbc:mysql://localhost:3306/ssafit?serverTimezone=UTC";
    private final String username = "ssafy";
    private final String password = "ssafy1234";
    private final String driverName = "com.mysql.cj.jdbc.Driver";

    private static DBUtil INSTANCE = new DBUtil();

    private DBUtil() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBUtil getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /*
     * Close all AutoCloseable objects, in given order.
     */
    public void close(AutoCloseable... closeables) {
        for (AutoCloseable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
