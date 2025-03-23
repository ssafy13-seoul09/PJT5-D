package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mysql DB 연결 객체를 제공해주고, 사용했던 자원을 해제하는 기능을 제공하는 클래스입니다.
 */
public class DBUtil {

    // DB Config
    private final String URL = "jdbc:mysql://localhost:3306/ssafit?serverTimezone=UTC";
    private final String USERNAME = "ssafy";
    private final String PASSWORD = "ssafy1234";
    private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    private static final DBUtil INSTANCE = new DBUtil();

    private DBUtil() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DBUtil getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
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

    @FunctionalInterface
    public interface PreparedStatementSetter {
        void setValues(PreparedStatement pstmt) throws SQLException;
    }

    @FunctionalInterface
    public interface ResultSetExtractor<T> {
        T extractData(ResultSet rs) throws SQLException;
    }

    /**
     * JDBC 쿼리 실행 위한 템플릿 메서드
     *
     * @param sql       쿼리문
     * @param setter    PreparedStatementSetter, 쿼리문 파라미터 설정 e.g) pstmt ->
     *                  pstmt.setString(1, userId)
     * @param extractor ResultSetExtractor, 데이터 추출용 함수 e.g) rs ->
     *                  DTOFactory.createUser(rs)
     * @return T, ResultSetExtractor 반환하는 타입
     */
    public <T> T executeQuery(String sql, PreparedStatementSetter setter, ResultSetExtractor<T> extractor) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            setter.setValues(pstmt);
            rs = pstmt.executeQuery();
            return extractor.extractData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(rs, pstmt, conn);
        }
    }

    /**
     * JDBC 쿼리 실행 위한 템플릿 메서드
     * 
     * @param sql    쿼리문
     * @param setter PreparedStatementSetter, 쿼리문 파라미터 설정 e.g) pstmt ->
     *               pstmt.setString(1, userId)
     * @return boolean, 쿼리 실행 성공 여부
     */
    public boolean executeUpdate(String sql, PreparedStatementSetter setter) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            setter.setValues(pstmt);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(pstmt, conn);
        }
    }
}
