package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentOperation {
    public static boolean insertStudent(Student student) {
        String query = "INSERT INTO students (id, name, age, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getAge());
            stmt.setString(4, student.getEmail());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            System.out.println("Insert failed: " + e.getMessage());
            return false;
        }
    }
    public static boolean updateStudent(Student student) {
        String query = "UPDATE students SET name = ?, age = ?, email = ? WHERE id = ?";

        try (Connection conn = Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getEmail());
            stmt.setInt(4, student.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }
    public static boolean deleteStudent(int studentId) {
        String query = "DELETE FROM students WHERE id = ?";

        try (Connection conn = Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (Exception e) {
            System.out.println("Delete failed: " + e.getMessage());
            return false;
        }
    }

}
