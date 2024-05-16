package com.example.parkingProject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserAccount {
    @Id
    @Column(name = "user_id", length = 50)
    private String userId;
    @Column(nullable = false)
    private String userPassword;
}
