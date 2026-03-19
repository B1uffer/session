package com.b1uffer.sessiontest.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {
    @Id
    private String name; // ROLE_EDITOR, ROLE_VIEWER 등등..

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> permissions = new HashSet<>();

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<String> getPermissions() {
        return permissions;
    }
}
