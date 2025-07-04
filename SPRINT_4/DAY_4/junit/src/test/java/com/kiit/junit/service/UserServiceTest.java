package com.kiit.junit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.kiit.junit.email.EmailSender;
import com.kiit.junit.model.User;
import com.kiit.junit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    EmailSender emailSender;

    @InjectMocks
    UserService userService;

    @Spy
    @InjectMocks
    UserService spyService;

    @Test
    void testProcessUser_withFallbackAndSuccess() {

        User user = new User("1", "Sankha", "sankha@example.com");


        when(userRepository.findUserById("1"))
                .thenReturn(null)
                .thenReturn(user);


        UserService service = spy(new UserService(userRepository, emailSender));


        assertThrows(RuntimeException.class, () -> service.processUser("1"));
        verify(service).handleMissingUser("1");


        service.processUser("1");


        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);

        verify(emailSender).send(emailCaptor.capture(), subjectCaptor.capture(), any());

        assertEquals("sankha@example.com", emailCaptor.getValue());
        assertEquals("Welcome", subjectCaptor.getValue());
    }
}

