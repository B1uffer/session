package com.b1uffer.sessiontest.service;

import com.b1uffer.sessiontest.entity.Post;
import com.b1uffer.sessiontest.security.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@NoArgsConstructor
public class JwtAuthorizationService {
    public void updateContent(String jwtToken, Post post, String newContent) {
        Claims claims = JwtUtils.parseToken(jwtToken);
        String userId = claims.getSubject();
        List<String> roles = claims.get("roles", List.class);

        if(!post.getOwner().equals(userId) && !roles.contains("ADMIN")) {
            throw new SecurityException("수정 권한이 없습니다.");
        }
        post.setContent(newContent);
    }
}
