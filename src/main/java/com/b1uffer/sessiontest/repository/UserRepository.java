package com.b1uffer.sessiontest.repository;

import com.b1uffer.sessiontest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    default boolean validate(String username, String password) {
        return "user".equals(username) && "password".equals(password);
    }
}
