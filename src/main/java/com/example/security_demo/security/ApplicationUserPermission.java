package com.example.security_demo.security;

public enum ApplicationUserPermission {
    MEMBER_READ("member:read"),
    MEMBER_WRITE("member:write"),
    EVENT_READ("event:read"),
    EVENT_WRITE("event:write"),
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
