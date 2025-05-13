package SPRINT_1_DAY_1;

import java.lang.reflect.Method;

public class metadata_1 {
    public static void main(String[] args) {
        String sample = "Hello";
        Class<?> clazz = sample.getClass();

        System.out.println("Name: " + clazz.getName());
        System.out.println("Simple Name: " + clazz.getSimpleName());
        System.out.println("Is Interface: " + clazz.isInterface());
        System.out.println("Methods:");
        for (Method method : clazz.getMethods()) {
            System.out.println(method.getName());
        }
    }
}
