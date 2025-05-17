package org.example;

public class CourseRecord {
    private String courseName;
    private String instructor;
    private String grade;

    public CourseRecord(String courseName, String instructor, String grade) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.grade = grade;
    }

    // Getters
    public String getCourseName() { return courseName; }
    public String getInstructor() { return instructor; }
    public String getGrade() { return grade; }

    public String toString() {
        return courseName + " , " + instructor + " , " + grade;
    }
}
