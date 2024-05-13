package com.example.parkingProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_state")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private Long stateId;
    @Column(name="car_number")
    private String carNumber;
    @Column(name="in_time")
    private LocalDateTime inTime;
    @Column(name="current_price")
    private Long currentPrice;
}