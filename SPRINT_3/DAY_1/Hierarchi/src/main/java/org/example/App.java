package org.example;

import org.example.controller.UserController;
public class App 
{
    public static void main( String[] args )
    {
        UserController controller = new UserController();

        controller.handleRequest(1); 
        controller.handleRequest(99);
    }
}
