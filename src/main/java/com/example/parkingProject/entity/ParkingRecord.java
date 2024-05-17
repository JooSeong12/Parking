package com.example.parkingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class ParkingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registerId;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    @Column(nullable = true)
    private Long parkingTime;
    private String carNumber;
    @Column(nullable = true)
    private Integer price;    // 소수점 계산후 올림하기 위한 float 타입(postgresql에서는 real타입)
    private Integer finalPrice;
    private String vigo;
}
