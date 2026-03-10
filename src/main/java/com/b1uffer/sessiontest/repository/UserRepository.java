package com.b1uffer.sessiontest.repository;

public class UserRepository {
    public boolean validate(String username, String password) {
        return "user".equals(username) && "password".equals(password);
    }
}
