package com.example.parkingProject.repository;

import com.example.parkingProject.entity.ParkingState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingStateRepository extends JpaRepository<ParkingState, Long> {
}
