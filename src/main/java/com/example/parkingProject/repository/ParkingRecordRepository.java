package com.example.parkingProject.Repository;

import com.example.parkingProject.entity.ParkingRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {
    @Query(value = "SELECT * FROM parking_record WHERE (DATE)in_time LIKE %:year%", nativeQuery = true)
    List<ParkingRecord> searchRecord(@Param("year")String year);

}
