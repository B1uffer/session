package com.b1uffer.sessiontest.repository;

import com.b1uffer.sessiontest.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
