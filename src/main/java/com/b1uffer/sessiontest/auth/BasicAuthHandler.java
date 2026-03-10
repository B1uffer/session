package com.b1uffer.sessiontest.auth;

import com.b1uffer.sessiontest.repository.UserRepository;

import java.util.Base64;

public class BasicAuthHandler {
    private final UserRepository userRepository;

    public BasicAuthHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 요청 헤더에서 Authorization을 받아서 검증함
    public boolean authenticate(String authHeader) {
        if(authHeader == null || !authHeader.startsWith("Basic ")) {
            return false; // 인증 헤더가 없음
        }

        // Basic 접두사 제거하기
        String base64Credentials = authHeader.substring("Basic ".length());

        // Base64 디코딩하기
        byte[] decodeByte = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(decodeByte);

        // 아이디와 비밀번호 분리하기
        String[] parts = credentials.split(":", 2);
        if(parts.length != 2) { // 검증로직, username:password 형태가 아니라 a:b:c 등이라면
            return false; // 분리가 잘못됐거나 뭔가 이상함
        }

        String username = parts[0];
        String password = parts[1];

        // 저장소에서 사용자를 검증함
        return userRepository.validate(username, password);
    }
}
