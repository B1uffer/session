package com.b1uffer.sessiontest.controller;

import com.b1uffer.sessiontest.entity.Post;
import com.b1uffer.sessiontest.repository.PostRepository;
import com.b1uffer.sessiontest.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PreAuthorize("hasPermission(#post, 'UPDATE')") // PreAuthorize
    @PutMapping("/{id}") // PutMapping
    public ResponseEntity<String> updatePost(@PathVariable Long id,
                                             @RequestBody Post post) {
        postService.update(id, post);
        return ResponseEntity.ok("Updated");
    }
}
