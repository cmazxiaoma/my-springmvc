package com.augmentum.exam.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.augmentum.exam.exception.DBException;
import com.augmentum.exam.jdbc.PropertyUtil;
import com.mysql.jdbc.Statement;

public class DBUtil {
    private static String driver, url, username, password;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    static {
        driver = PropertyUtil.getProperty("driver");
        url = PropertyUtil.getProperty("url");
        username = PropertyUtil.getProperty("username");
        password = PropertyUtil.getProperty("password");
        System.out.println("driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password);
    }

    public static DBUtil getInstance() {
        return DBUtilHolder.DB_UTIL;
    }

    private static class DBUtilHolder {
        private static final DBUtil DB_UTIL = new DBUtil();
    }

    public Connection getConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return conn;
    }

    public void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    public List<Map<String, Object>> queryAll(String sql, Object[] params){
        stmt = getPreparedStatement(sql, params);
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        try{
            rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for(int i = 1; i <= count; i++){
                    String colunmName = metaData.getColumnName(i);
                    Object value = rs.getObject(colunmName);
                    map.put(colunmName, value);
                }
                list.add(map);
            }
        } catch (Exception e) {
            throw new DBException();
        } finally {
            close(rs, stmt, conn);
        }
        return list;
    }

    public PreparedStatement getPreparedStatement(String sql, Object[] params){
        conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            throw new DBException();
        }

        int count=params.length;
        for(int i = 0; i < count; i++){
            try {
                stmt.setObject((i+1), params[i]);
            } catch (Exception e) {
                throw new DBException();
            }
        }
        return stmt;
    }

    public void setAutoCommit(Connection conn, boolean autoCommit) {
        try {
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    public void commit(Connection conn) {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }

    public void rollback(Connection connection){
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
    }
}
