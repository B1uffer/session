package com.b1uffer.sessiontest.auth;

import java.security.SecureRandom;
import java.util.Base64;

public class CsrfPractice {
    public CsrfPractice() {
        // 1. 토큰 발급하기
        byte[] buf = new byte[32];
        new SecureRandom().nextBytes(buf);
        String csrf = Base64.getUrlEncoder().withoutPadding().encodeToString(buf);
//        session.setAttribute("CSRF", csrf); // 세션 저장소에 보관

//        String form = "<form method=\"POST\" action=\"/transfer\">"+
//                "<input type=\"hidden\" name=\"csrf\" value=\""+csrf+"\">"+
//                "...</form>";
//
//        // 3. 검증하기
//        String presented = request.getParameter("csrf");
//        String stored = (String)session.getAttribute("CSRF");
//        if(stored == null || !stored.equals(presented)) {
//            response.setStatus(403); // CSRF 실패
//            return;
//        }
    }
}
