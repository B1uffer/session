package com.b1uffer.sessiontest;

import com.b1uffer.sessiontest.auth.BasicAuthHandler;
import com.b1uffer.sessiontest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasicAuthHandlerTest {
    private BasicAuthHandler authHandler;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        authHandler = new BasicAuthHandler(userRepository);
    }

    @Test
    void authenticate_success() {
        // Given
        String username = "B1uffer";
        String password = "password123";
        String credentials = username + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        String authHeader = "Basic " + encodedCredentials;

        System.out.println("authHeader : " + authHeader);

        when(userRepository.validate(username, password)).thenReturn(true);

        // when
        boolean result = authHandler.authenticate(authHeader);
        System.out.println("result : " + result);
        // then
        assertTrue(result);
        verify(userRepository).validate(username, password);
    }
}
