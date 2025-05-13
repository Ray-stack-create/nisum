package SPRINT_1_DAY_1;

class Student{
    private String name;
    private String adress;
    private String section;
    private int roll;
    private String studentClass;

    private static String college;
    private static int count=0;

    public Student(String name, String adress, String section, String studentClass) {
        this.name = name;
        this.adress = adress;
        this.section = section;
        this.roll = count++;
        this.studentClass = studentClass;
    }

    static{
        college="KIIT University";
    }

    public void print(){
        System.out.println("Student Name: "+name);
        System.out.println("Roll no: "+roll);
        System.out.println("Section: "+section);
        System.out.println("Class: "+studentClass);
        System.out.println("Adress: "+adress);
        System.out.println("College: "+college);

    }
}

public class studentInfo_9 {
    public static void main(String[] args) {
        Student s1 = new Student("Sankha","West Bengal", "CSE-22", "CSE");
        Student s2 = new Student("Sohani","Odisha", "CSE-34", "CSE");

        s1.print();
        s2.print();
    }
}
