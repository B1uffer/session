package com.b1uffer.sessiontest.controller;

import com.b1uffer.sessiontest.entity.User;
import com.b1uffer.sessiontest.security.UserPrincipal;
import com.b1uffer.sessiontest.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        User updateUser = userService.get(username);
        return updateUser;
    }

    /**
     * 관리자 전용 api
     */
    @PostMapping
    public User createUser(@RequestBody User user, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if(!userPrincipal.isAdmin()) {
            throw new SecurityException("관리자만 등록 가능합니다.");
        }
        User createUser = userService.create(user.getUsername(), user.getPassword());
        return createUser;
    }
}
