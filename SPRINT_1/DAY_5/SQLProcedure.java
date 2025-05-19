package org.example;
import java.sql.*;
import java.util.Scanner;
public class SQLProcedure {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "username";
        String password = "password";
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            String callSQL = "{CALL getStudentById(?)}";
            cstmt = conn.prepareCall(callSQL);
            cstmt.setInt(1, id);
            rs = cstmt.executeQuery();
            boolean found = false;
            while (rs.next()) {
                int studentId = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("ID: " + studentId + ", Name: " + name + ", Age: " + age);
                found = true;
            }

            if (!found) {
                System.out.println("No student found with the given ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (cstmt != null) cstmt.close();
                if (conn != null) conn.close();
                scanner.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
