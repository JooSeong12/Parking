package com.example.parkingProject.repository;

import com.example.parkingProject.entity.ParkingState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingStateRepository extends JpaRepository<ParkingState, Long> {
    Page<ParkingState> findByCarNumberContains(String keyword, Pageable pageable);
}
