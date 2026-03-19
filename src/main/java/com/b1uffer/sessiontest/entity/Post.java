package com.b1uffer.sessiontest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 포스트 아이디

    @Column(nullable = false)
    private String owner; // 작성자

    @Column(nullable = false)
    private String content; // 내용

    public Long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getContent() {
        return content;
    }
}
