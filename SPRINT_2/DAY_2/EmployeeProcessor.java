package SPRINT_2_DAY_2;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Address {
    private String city;
    private String country;

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}

enum Gender { MALE, FEMALE }

class Employee {
    private int empId;
    private String firstName;
    private String lastName;
    private String department;
    private Gender gender;
    private double salary;
    private Optional<String> email;
    private Optional<Address> address;

    public Employee(int empId, String firstName, String lastName, String department, Gender gender,
                    double salary, String email, Address address) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.gender = gender;
        this.salary = salary;
        this.email = Optional.ofNullable(email);
        this.address = Optional.ofNullable(address);
    }

    public int getEmpId() { return empId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDepartment() { return department; }
    public Gender getGender() { return gender; }
    public double getSalary() { return salary; }
    public Optional<String> getEmail() { return email; }
    public Optional<Address> getAddress() { return address; }

    public void increaseSalary(double amount) { this.salary += amount; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return getFullName() + " [" + department + ", " + gender + ", $" + salary + ", Email: " +
                email.orElse("N/A") + ", Address: " + address.orElse(new Address("DefaultCity", "DefaultCountry")) + "]";
    }
}

public class EmployeeProcessor {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
             new Employee(101, "Sankhadip", "Roy", "SDE", Gender.MALE, 85000, "sankha@gmail.com", new Address("Paris", "France")),
            new Employee(102, "Diya", "Manna", "HR", Gender.FEMALE, 70000, "diya@gmail.com", null),
            new Employee(103, "Samridhi", "Kundu", "IT", Gender.FEMALE, 65000, null, new Address("Berlin", "Germany")),
            new Employee(104, "Sohani", "Hazra", "Sales", Gender.FEMALE, 72000, "sohani@gmail.com", new Address("London", "UK")),
            new Employee(105, "Satyaki", "Maiti", "ML", Gender.MALE, 53000, "satyaki@gmail.com", null),
            new Employee(106, "Subhadeep", "Bhadra", "Consulting", Gender.MALE, 60000, "subhadeep@gmail.com", new Address("Rome", "Italy")),
            new Employee(107, "Anannya", "Das", "Finance", Gender.FEMALE, 67000, null, new Address("Madrid","Spain")));

        // 1
        System.out.println("1. Full name of first employee: " + employees.get(0).getFullName());

        // 2
        Map<String, Long> deptCount = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("\n2. Department counts: " + deptCount);

        // 3
        Map<String, List<Employee>> deptMap = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment));
        List<Employee> searched = deptMap.values().stream()
            .flatMap(List::stream)
            .filter(e -> e.getFirstName().toLowerCase().contains("li")
                      || e.getLastName().toLowerCase().contains("li"))
            .collect(Collectors.toList());
        System.out.println("\n3. Search 'li': " + searched);

        // 4
        System.out.println("\n4. Padded Store ID '23': " + String.format("%4s", "23").replace(' ', '0'));

        // 5
        System.out.println("\n5. Not in HR: " + employees.stream()
            .filter(e -> !e.getDepartment().equals("HR"))
            .collect(Collectors.toList()));

        // 6
        System.out.println("\n6. Sorted by First Name:");
        employees.stream()
            .sorted(Comparator.comparing(Employee::getFirstName))
            .forEach(System.out::println);

        // 7
        System.out.println("\n7. Highest EmpId:");
        employees.stream()
            .max(Comparator.comparingInt(Employee::getEmpId))
            .ifPresent(System.out::println);

        // 8
        String concatenated = employees.stream()
            .map(e -> e.getFirstName() + e.getLastName())
            .collect(Collectors.joining("|"));
        System.out.println("\n8. Concatenated full names: " + concatenated);

        // 9
        Employee eighth = employees.get(7);
        System.out.println("\n9. 8th Employee: " + eighth.getFullName() + " - " + eighth.getDepartment());

        // 10
        List<Integer> idsToFind = Arrays.asList(102, 104, 110);
        List<Employee> matched = employees.stream()
            .filter(e -> idsToFind.contains(e.getEmpId()))
            .collect(Collectors.toList());
        System.out.println("\n10. Matching IDs: " + matched);

        // 11
        Map<Gender, Long> genderCounts = employees.stream()
            .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println("\n11. Gender Counts: " + genderCounts);

        // 12
        String genderGroup = employees.stream()
            .collect(Collectors.groupingBy(Employee::getGender,
                    Collectors.mapping(Employee::getFirstName, Collectors.joining("-"))))
            .entrySet().stream()
            .map(e -> e.getKey() + ": [" + e.getValue() + "]")
            .collect(Collectors.joining(", "));
        System.out.println("\n12. Gender groups: " + genderGroup);

        // 13
        System.out.println("\n13. Sorted by salary:");
        employees.stream()
            .sorted(Comparator.comparingDouble(Employee::getSalary))
            .forEach(System.out::println);

        // 14
        System.out.println("\n14. Optional fields usage:");
        employees.forEach(e -> System.out.println(e.toString()));

        // 15
        System.out.println("\n15. Default address if missing:");
        employees.forEach(e -> System.out.println(e.getFullName() + " - Address: " +
            e.getAddress().orElse(new Address("DefaultCity", "DefaultCountry"))));

        // 16
        System.out.println("\n16. Salary increase for IT:");
        employees.stream()
            .filter(e -> "IT".equals(e.getDepartment()))
            .forEach(e -> e.getAddress().ifPresent(a -> e.increaseSalary(5000)));
        employees.stream()
            .filter(e -> "IT".equals(e.getDepartment()))
            .forEach(System.out::println);

        // 17
        System.out.println("\n17. Sorted addresses by city, then country:");
        employees.stream()
            .map(e -> e.getAddress().orElse(new Address("DefaultCity", "DefaultCountry")))
            .distinct()
            .sorted(Comparator.comparing(Address::getCity).thenComparing(Address::getCountry))
            .forEach(System.out::println);

        // 18
        Map<String, Address> fullNameToAddress = employees.stream()
            .filter(e -> e.getAddress().isPresent())
            .collect(Collectors.toMap(
                e -> e.getFirstName() + e.getLastName(),
                e -> e.getAddress().get()
            ));
        System.out.println("\n18. Map<FullName, Address>: " + fullNameToAddress);

        // 19
        System.out.println("\n19. findAny and findFirst:");
        employees.stream().findAny().ifPresent(e -> System.out.println("Any: " + e));
        employees.stream().findFirst().ifPresent(e -> System.out.println("First: " + e));

        // 20
        System.out.println("\n20. Match operations:");
        System.out.println("Any from HR? " +
            employees.stream().anyMatch(e -> "HR".equals(e.getDepartment())));
        System.out.println("All have email? " +
            employees.stream().allMatch(e -> e.getEmail().isPresent()));
        System.out.println("No employee has null name? " +
            employees.stream().noneMatch(e -> e.getFirstName() == null || e.getLastName() == null));
    }
}
