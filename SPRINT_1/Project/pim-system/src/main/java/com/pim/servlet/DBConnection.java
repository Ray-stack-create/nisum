package com.pim.servlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/pim_db?useSSL=false&serverTimezone=UTC";
    ;
    private static final String USER = "root";
    private static final String PASSWORD = "beluhecker8@";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // For MySQL 8+

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
