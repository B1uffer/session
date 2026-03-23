package com.b1uffer.sessiontest.service;

import com.b1uffer.sessiontest.entity.Post;
import com.b1uffer.sessiontest.entity.User;
import com.b1uffer.sessiontest.repository.PostRepository;
import com.b1uffer.sessiontest.security.UserPrincipal;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class PostService {
    private PostRepository postRepository;

    // 게시글 생성 로직
    public Post create(Long id, Post post) {
        Post findPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("post not found"));
        post.setContent(post.getContent());
        postRepository.save(findPost);
        return findPost;
    }

    public void update(Post post, String newContent) {
        // 인증 로직
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        // 작성자 본인이거나 관리자일 때 업데이트 가능
        if(!post.getOwner().equals(principal.getUsername()) || !principal.isAdmin()) {
            log.info("게시글 수정 실패");
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        post.setContent(newContent);
        log.info("게시글 수정 완료");
    }

    // HttpSession을 활용한 권한 확인 예시
    public void updatePost(HttpSession session, Post post, String newContent) {
        User user = (User) session.getAttribute("user");
        if(!post.getOwner().equals(user.getUsername()) && !user.getRoles().contains("ADMIN")) {
            throw new SecurityException("수정 권한이 없습니다.");
        }
        post.setContent(newContent);
    }

    public void delete(Post post) {
        // 인증 로직
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        // 작성자 본인이거나 관리자일 때 삭제 가능
        if(!post.getOwner().equals(principal.getUsername()) || !principal.isAdmin()) {
            log.info("게시글 삭제 실패");
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
        postRepository.delete(post);
        log.info("게시글 삭제 완료");
    }
}
