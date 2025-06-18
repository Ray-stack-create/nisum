package com.nisum.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testUserProperties() {
        User user = new User("Sankha", "sankha@example.com", 25);

        assertAll("User fields",
                () -> assertEquals("Sankha", user.getName()),
                () -> assertEquals("sankha@example.com", user.getEmail()),
                () -> assertEquals(25, user.getAge()),
                () -> assertNotNull(user.getEmail()),
                () -> assertTrue(user.getAge() > 18)
        );
    }

    @Test
    void testValidateAgeThrowsException() {
        UserService service = new UserService();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.validateAge(16)
        );

        assertEquals("Underage", exception.getMessage());
    }
}

