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

    public User create(String username, String password) {
        if(username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        if(username.length() > 12) {
            throw new IllegalArgumentException("Username cannot be longer than 12 characters");
        }

        if(password.length() < 7 || password.length() > 15) {
            throw new IllegalArgumentException("비밀번호는 8자리에서 14자리 사이입니다.");
        }

        User user = new User(username, password);
        userRepository.save(user);
        return user;
    }

    public User get(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public boolean validate(String username, String password) {
        return "user".equals(username) && "password".equals(password);
    }
}
