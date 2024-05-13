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
    @Query(value = "SELECT * FROM parking_record WHERE DATE(in_time)= :dateTime ORDER BY register_id LIMIT 10 OFFSET 1", nativeQuery = true)
    Page<ParkingRecord> searchRecord(@Param("dateTime") LocalDate dateTime,
                                     @Param("pageable") Pageable pageable);

    Page<ParkingRecord> findByInTimeBetweenContains(LocalDateTime startDate,LocalDateTime endDate , Pageable pageable);
}
