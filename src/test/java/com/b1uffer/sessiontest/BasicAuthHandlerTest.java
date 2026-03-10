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

        System.out.println("credentials : " + credentials);
        System.out.println("authHeader : " + authHeader);

        when(userRepository.validate(username, password)).thenReturn(true);

        // when
        boolean result = authHandler.authenticate(authHeader);
        System.out.println("result : " + result);
        // then
        assertTrue(result);
        verify(userRepository).validate(username, password);
    }

    @Test
    void authenticate_fail_password() {
        //Given
        String username = "B1uffer";
        String password = "password123";
        String credentials = username + ":" + password;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
        when(userRepository.validate("B1uffer", "wrongpassword")).thenReturn(false);
        System.out.println("credentials : " + credentials);

        // when
        boolean result = authHandler.authenticate(authHeader);
        System.out.println("result : " + result);

        // then
        assertFalse(result);
    }

    @Test
    void authenticate_fail_header() {
        // 필터링에 대해서 테스트함
        assertFalse(authHandler.authenticate(null));
        assertFalse(authHandler.authenticate("Bearer anyToken"));
    }

    @Test
    void authenticate_fail_credentials() {
       // Base64의 형식이 맞지 않는 경우, : 빠져있음
        String authHeader = "Basic " + Base64.getEncoder().encodeToString("B1ufferPassword123".getBytes());
        when(userRepository.validate("B1uffer", "password123")).thenReturn(false);

        // when
        boolean result = authHandler.authenticate(authHeader);

        // then
        assertFalse(result);
    }
}
