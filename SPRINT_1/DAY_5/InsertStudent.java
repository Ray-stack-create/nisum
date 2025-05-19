package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class InsertStudent {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "username";
        String password = "password";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +"id INT PRIMARY KEY, "+"name VARCHAR(100), "+"age INT)";
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table created or already exists.");
            int id = 101;
            String name = "Alice";
            int age = 20;
            String insertSQL = "INSERT INTO students (id, name, age) VALUES (" +id + ", '" + name + "', " + age + ")";
            int rowsInserted = stmt.executeUpdate(insertSQL);
            System.out.println(rowsInserted + " row(s) inserted.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
