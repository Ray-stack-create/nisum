package SPRINT_1_DAY_2;

import java.util.Scanner;
import java.util.TreeSet;


class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private String dept;
    private double salary;

 public Employee(int id, String name, String dept, double salary) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Employee other) {
        return this.name.compareToIgnoreCase(other.name); 
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department: " + dept + ", Salary: $" + salary;
    }
}

public class EmployeeRecords_TreeSet_6 {
    public static void main(String[] args) {
        TreeSet<Employee> employeeSet = new TreeSet<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();

                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();

                    Employee emp = new Employee(id, name, dept, salary);
                    if (employeeSet.add(emp)) {
                        System.out.println("Employee added successfully.");
                    } else {
                        System.out.println("Employee with this name already exists.");
                    }
                    break;

                case 2:
                    if (employeeSet.isEmpty()) {
                        System.out.println("No employees found.");
                    } else {
                        System.out.println("\nEmployee List (sorted by name):");
                        for (Employee e : employeeSet) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 3);

        scanner.close();
    }
}
