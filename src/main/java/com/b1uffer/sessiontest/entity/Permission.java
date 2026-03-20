package com.b1uffer.sessiontest.entity;

import java.util.Objects;

public class Permission {
    private final ResourceType resourceType;
    private final Action action;

    public Permission(ResourceType resourceType, Action action) {
        this.resourceType = resourceType;
        this.action = action;
    }

    // getter
    public ResourceType getResourceType() {
        return resourceType;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceType, action);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Permission)) {
            return false;
        }
        Permission permission = (Permission) obj;

        return permission.resourceType == resourceType && permission.action == action;
    }

    @Override
    public String toString() {
        return resourceType + ":" + action;
    }
}
