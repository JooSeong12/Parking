package com.example.parkingProject.entity;

import com.example.parkingProject.constant.MembershipType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Membership {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String phone;
    private LocalDate membershipEnd;
    private LocalDate membershipStart;
    private Integer charge;
    private String name;
    private String carNumber;
    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;
}
