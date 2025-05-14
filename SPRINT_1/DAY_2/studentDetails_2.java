package SPRINT_1_DAY_2;

import java.util.ArrayList;
import java.util.Scanner;

class Student{
    String name;
    String id;
    String grade;


    public Student(String name, String id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    public String showDetails(){
        return "ID: "+id+", Name: "+name+", Grade: "+grade;
    }
}

class StudentManagement{
    private ArrayList<Student> students;

    public StudentManagement(){
        students = new ArrayList<>();

    }

    public void addStudent(String name, String id, String grade){
        students.add(new Student(name, id, grade));
        System.out.println("Student Detils added");
    }

 public void removeStudent(String id) {
        boolean removed = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).id.equalsIgnoreCase(id)) {
                students.remove(i);
                System.out.println("Student with ID " + id + " removed.");
                removed = true;
                break;
            }

        }
        if (!removed) {
            System.out.println("Mentioned Student with ID " + id + " not found.");
        }
    }


     public void searchStudent(String id) {
        for (Student student : students) {
            if (student.id.equalsIgnoreCase(id)) {
                System.out.println("Student Found: " + student);
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("All Students:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

}

public class studentDetails_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StudentManagement manage = new StudentManagement();

        int choice;
        do {
            System.out.println("Select any from the choices");
            System.out.println("1. Add student");
            System.out.println("2. Remove student");
            System.out.println("3. Search student by ID");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine();
                    manage.addStudent(name,id,grade);
                    break;
                    
                case 2:
                    System.out.print("Enter student ID to remove: ");
                    String removeId = sc.nextLine();
                    manage.removeStudent(removeId);
                    break;

                case 3:
                    System.out.print("Enter student ID to search: ");
                    String searchId = sc.nextLine();
                    manage.searchStudent(searchId);
                    break;

                case 4:
                    manage.displayAllStudents();
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
    }while (choice!=5) ;
        sc.close();
    }
}
