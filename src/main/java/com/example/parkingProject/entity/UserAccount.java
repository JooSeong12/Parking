package com.example.parkingProject.entity;

import com.example.parkingProject.constant.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserAccount {
    @Id
    @Column(name = "user_id", length = 50)
    private String userId;
    @Column(nullable = false)
    private String userPassword;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
