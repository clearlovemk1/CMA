package com.springboot;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private static final String USER = "root"; // 替换为你的数据库用户名
    private static final String PASSWORD = "Kimi.2"; // 替换为你的数据库密码

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
