package com.b1uffer.sessiontest.service;

import com.b1uffer.sessiontest.entity.User;
import com.b1uffer.sessiontest.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User create(String username, int age) {
        if(username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        if(username.length() > 12) {
            throw new IllegalArgumentException("Username cannot be longer than 12 characters");
        }

        if(age >= 123 || age < 0) {
            throw new IllegalArgumentException("올바른 나이를 입력하세요.");
        }

        User user = new User(username, age);
        userRepository.save(user);
        return user;
    }

    public boolean validate(String username, String password) {
        return "user".equals(username) && "password".equals(password);
    }
}
