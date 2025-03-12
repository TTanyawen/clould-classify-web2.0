package com.example.java7_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TestSqlConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/clouddb2.0";
        String username = "root";
        String password = "2640";


        try {
            // 加载 MySQL JDBC 驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            // 在这里执行你的数据库操作

            // 关闭连接
            connection.close();
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }

}
