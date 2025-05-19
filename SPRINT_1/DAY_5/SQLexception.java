package org.example;
import java.sql.*;
import java.util.Scanner;
public class SQLexception {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "username";
        String password = "password";
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter partial name to search: ");
            String partialName = scanner.nextLine();
            try (
                    Connection conn = DriverManager.getConnection(url, user, password);
                    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM students WHERE name LIKE ?");
            ) {
                pstmt.setString(1, partialName + "%");
                try (ResultSet rs = pstmt.executeQuery()) {
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
                }

            } catch (SQLException e) {
                System.out.println("SQL Error Occurred:");
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("Error Code: " + e.getErrorCode());
                System.out.println("Message: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}

