package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RollbackTransaction {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "username";
        String password = "password";
        Connection conn = null;
        PreparedStatement stmtStudent = null;
        PreparedStatement stmtDetails = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            String insertStudentSQL = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
            stmtStudent = conn.prepareStatement(insertStudentSQL);
            stmtStudent.setInt(1, 564);
            stmtStudent.setString(2, "Sankha");
            stmtStudent.setInt(3, 20);
            stmtStudent.executeUpdate();


            String insertDetailsSQL = "INSERT INTO student_details (student_id, address, phone) VALUES (?, ?, ?)";
            stmtDetails = conn.prepareStatement(insertDetailsSQL);
            stmtDetails.setInt(1, 564);
            stmtDetails.setString(2, "BaniVihar");
            stmtDetails.setString(3, "9876543210");
            stmtDetails.executeUpdate();
            conn.commit();
            System.out.println("Both inserts successful. Transaction committed.");

        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Error occurred. Transaction rolled back.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();

        } finally {
            try {
                if (stmtStudent != null) stmtStudent.close();
                if (stmtDetails != null) stmtDetails.close();
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}
