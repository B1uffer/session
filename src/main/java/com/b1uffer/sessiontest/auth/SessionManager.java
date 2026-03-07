package com.b1uffer.sessiontest.auth;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private Map<String, Session> store = new ConcurrentHashMap<>();

    // 세션 생성 메서드
    public Session create(String userId, long ttlSeconds) {
        String id = UUID.randomUUID().toString();
        Session session = new Session(id, userId, ttlSeconds);
        store.put(id, session);
        return session;
    }
}
