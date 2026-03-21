package com.b1uffer.sessiontest.service;

import com.b1uffer.sessiontest.entity.Post;
import com.b1uffer.sessiontest.repository.PostRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class PostService {
    private PostRepository postRepository;

    public Post create(Long id, Post post) {
        Post findPost = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("post not found"));
        post.setContent(post.getContent());
        postRepository.save(findPost);
        return findPost;
    }

    public void update(Post post, String newContent) {

    }
}
