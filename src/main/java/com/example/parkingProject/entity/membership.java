package com.example.parkingProject.entity;

import com.example.parkingProject.constant.MemberShipType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class membership {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int member_id;
    private String phone;
    private LocalDate membershipEnd;
    private LocalDate membershipStart;
    private Integer charge;
    private String name;
    private String carNumber;
    @Enumerated(EnumType.STRING)
    private MemberShipType membershipType;
}
