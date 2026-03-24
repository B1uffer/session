package com.b1uffer.sessiontest.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Session {
    private String id; // 세션 ID
    private String userId; // 사용자 ID
    private long expiresAt; // 만료 시각

    public Session(String id, String userId, long ttlSeconds) {
        this.id = id;
        this.userId = userId;
        this.expiresAt = System.currentTimeMillis() + ttlSeconds * 1000;
    }

    public boolean isExpired() {
        // expiresAt이 현재시간보다 작다면 만료됨
        return System.currentTimeMillis() > expiresAt;
    }
}
