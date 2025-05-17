package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRetreive {

    public static Student getStudentById(int studentId) {
        String query = "SELECT * FROM students WHERE id = ?";

        try (Connection conn = Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");

                return new Student(id, name, age, email);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving student: " + e.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {
        Student student = getStudentById(101);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }
}
