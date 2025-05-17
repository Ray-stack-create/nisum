package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FetchCourse {
    public static List<CourseRecord> getCoursesByStudentId(int studentId) {
        List<CourseRecord> records = new ArrayList<>();
        String query = "SELECT c.course_name, c.instructor, e.grade FROM enrollments e JOIN courses c ON e.course_id = c.id WHERE e.student_id = ?";
        try (Connection conn = Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String courseName = rs.getString("course_name");
                String instructor = rs.getString("instructor");
                String grade = rs.getString("grade");

                CourseRecord record = new CourseRecord(courseName, instructor, grade);
                records.add(record);
            }

        } catch (Exception e) {
            System.out.println("Error fetching course records: " + e.getMessage());
        }

        return records;
    }

    public static void main(String[] args) {
        List<CourseRecord> records = getCoursesByStudentId(1);
        for (CourseRecord r : records) {
            System.out.println(r);
        }
    }
}
