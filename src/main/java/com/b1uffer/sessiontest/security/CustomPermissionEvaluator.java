package com.b1uffer.sessiontest.security;

import com.b1uffer.sessiontest.entity.Post;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject,
                                 Object permission) {
        /**
         * 소유자만 수정 가능하게끔 하기
         */
        if(targetDomainObject instanceof Post post && "UPDATE".equals(permission)) {
            String currentUser = authentication.getName(); // 인증하는 사람 이름
            return post.getOwner().equals(currentUser); // currentUser와 게시글 작성자의 이름 비교
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        // 커스텀 ID 기반 정책 구현하는곳
        return false;
    }
}
