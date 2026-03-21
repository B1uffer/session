package com.b1uffer.sessiontest.controller;

import com.b1uffer.sessiontest.entity.User;
import com.b1uffer.sessiontest.security.UserPrincipal;
import com.b1uffer.sessiontest.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @GetMapping("{username}")
    public User getUser(@PathVariable String username, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if(!userPrincipal.getUsername().equals(username) || !userPrincipal.isAdmin()) {
            throw new SecurityException("Invalid username or password");
        }
        User user = userService.get(username);
        return user;
    }
}
