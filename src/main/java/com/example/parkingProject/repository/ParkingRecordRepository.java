package com.example.parkingProject.repository;

import com.example.parkingProject.entity.ParkingRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {

    // 날짜로만 검색
    Page<ParkingRecord> findByInTimeBetweenOrderByInTime(LocalDateTime startDate,LocalDateTime endDate , Pageable pageable);

    // 날짜에 해당하는 차량번호로 검색
    Page<ParkingRecord> findByInTimeBetweenAndCarNumberContains(LocalDateTime startDate, LocalDateTime endDate, String keyword, Pageable pageable);

    // 차량 번호로만 검색
    Page<ParkingRecord> findByCarNumberContains(String keyword, Pageable pageable);
}
