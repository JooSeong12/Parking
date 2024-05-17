package com.example.parkingProject.constant;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    private final String desc;

    UserRole(String desc) {
        this.desc = desc;
    }
}
