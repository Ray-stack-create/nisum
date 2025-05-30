package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        System.out.println("\n--- Using Constructor Injection ---");
        TextEditor teConstructor = (TextEditor) context.getBean("textEditorConstructor");
        teConstructor.spellCheck();

        System.out.println("\n--- Using Setter Injection ---");
        TextEditorSetter teSetter = (TextEditorSetter) context.getBean("textEditorSetter");
        teSetter.spellCheck();
    }
}
