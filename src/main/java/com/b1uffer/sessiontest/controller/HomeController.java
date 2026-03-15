package com.b1uffer.sessiontest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {
    @GetMapping("/hello-oauth2")
    public String home() {
        log.info("OAuth 인증 감지");
        return "hello-oauth2";
    }
}
