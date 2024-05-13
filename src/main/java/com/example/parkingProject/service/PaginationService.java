package com.example.parkingProject.service;

import com.example.parkingProject.dto.ParkingRecordDto;
import com.example.parkingProject.entity.ParkingRecord;
import com.example.parkingProject.repository.ParkingRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {
    private final ParkingRecordRepository parkingRecordRepository;
    private static final int BAR_LENGTH=5;

    public PaginationService(ParkingRecordRepository parkingRecordRepository) {
        this.parkingRecordRepository = parkingRecordRepository;
    }

    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages) {
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);
        return IntStream.range(startNumber, endNumber).boxed().toList();
    }

    public Page<ParkingRecord> pagingList(Pageable pageable) {
        return parkingRecordRepository.findAll(pageable);
    }

    public Page<ParkingRecord> searchRecord(int year, int month, int day, Pageable pageable) {
        LocalDateTime startDate = LocalDateTime.of(year,month,day,0,0,0);
        LocalDateTime endDate = LocalDateTime.of(year,month,day, 23, 59, 59);
        Page<ParkingRecord> pageList = parkingRecordRepository.findByInTimeBetweenContains(startDate,endDate,pageable);
        return pageList;
    }
}
