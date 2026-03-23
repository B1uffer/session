package com.b1uffer.sessiontest.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtUtils {
    private static final String SECRET = "mysecretkey123";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
