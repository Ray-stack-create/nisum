package SPRINT_1_DAY_1;

class Student{
    private int math;
    private int english;
    private int chemistry;
    private int physics;
    private String college;

    public Student(int math, int english, int chemistry, int physics, String college) {
        this.math = math;
        this.english = english;
        this.chemistry = chemistry;
        this.physics = physics;
        this.college = college;
    }

    public void printInfo(){
        System.out.println("College Name: "+college);
        System.out.println("Math Marks: "+math);
        System.out.println("Physics Marks: "+physics);
        System.out.println("Chemistry Marks: "+chemistry);
        System.out.println("English Marks: "+english);

    }
    
}

public class studentInfo_15 {
    public static void main(String[] args) {
        Student s1 = new Student(67, 34, 56, 93,"KIIT University");
        Student s2 = new Student(57, 57, 85, 48, "KIIT University");

        s1.printInfo();
        s2.printInfo();
    }
}
