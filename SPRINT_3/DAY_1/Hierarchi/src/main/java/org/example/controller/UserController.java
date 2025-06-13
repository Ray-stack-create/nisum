package org.example.controller;
import org.example.model.User;
import org.example.service.UserService;
import org.example.exception.UserNotFoundException;

public class UserController {
    private final UserService service = new UserService();

    public void handleRequest(int userId) {
        try {
            User user = service.getUserById(userId);
            System.out.println("User Found: " + user.getName());
        } catch (UserNotFoundException e) {
            System.err.println("ERROR in Controller: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("General error: " + e.getMessage());
        }
    }
}
