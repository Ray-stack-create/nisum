package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchByName {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "username";
        String password = "password";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.print("Enter partial name to search: ");
            String partialName = scanner.nextLine();
            String searchSQL = "SELECT * FROM students WHERE name LIKE ?";
            pstmt = conn.prepareStatement(searchSQL);
            pstmt.setString(1, partialName + "%");
            rs = pstmt.executeQuery();
            boolean found = false;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
                found = true;
            }

            if (!found) {
                System.out.println("No students found matching the input.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                scanner.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
