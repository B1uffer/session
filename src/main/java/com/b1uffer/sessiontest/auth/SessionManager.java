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

    // 세션 조회
    public Session find(String id) {
        Session session = store.get(id);

        // 검증로직
        if (session == null || session.isExpired()) { // session이 없거나 만료됐다면
            store.remove(id); // store에서 id를 제거함
            return null;
        }
        return session;
    }
}
