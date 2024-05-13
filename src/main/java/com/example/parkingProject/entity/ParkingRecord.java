package com.example.parkingProject.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class ParkingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registerId;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    @Column(nullable = true)
    private float parkingTime;
    private String carNumber;
    @Column(nullable = true)
    private int price;    // 소수점 계산후 올림하기 위한 float 타입(postgresql에서는 real타입)
}
