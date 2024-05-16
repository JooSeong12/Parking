package com.example.parkingProject.service;

import com.example.parkingProject.dto.ParkingRecordDto;
import com.example.parkingProject.entity.ParkingRecord;
import com.example.parkingProject.repository.ParkingRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public Page<ParkingRecord> searchRecord(Long year, Long month, Long day, String keyword, Pageable pageable) {
        if (StringUtils.isEmpty(year) && StringUtils.isEmpty(month) && StringUtils.isEmpty(day)){
            Page<ParkingRecord> pageList=parkingRecordRepository.findByCarNumberContains(keyword, pageable);
            return pageList;
        } else if(StringUtils.isEmpty(month) && StringUtils.isEmpty(day)){
            // 연도만 입력
            LocalDateTime startDate = LocalDateTime.of(Math.toIntExact(year),1,1,0,0,0);    // 연도의 처음날(1월1일)
            LocalDateTime endDate = LocalDateTime.of(Math.toIntExact(year),12,31, 23, 59, 59);  // 연도의 마지막 날 (12월31일)
            Page<ParkingRecord> pageList;
            if(keyword.isEmpty()) { // 차랑번호 입력 유무
                pageList = parkingRecordRepository.findByInTimeBetweenOrderByInTime(startDate, endDate, pageable);
            } else {
                pageList = parkingRecordRepository.findByInTimeBetweenAndCarNumberContains(startDate, endDate, keyword, pageable);
            }
            return pageList;
        } else if (StringUtils.isEmpty(day)) {
            // 연도, 월까지 입력
            LocalDate localDate = LocalDate.of(Math.toIntExact(year), Math.toIntExact(month), 1);   // 각 월의 마지막날을 구하기위한 날짜선언
            LocalDateTime startDate = LocalDateTime.of(Math.toIntExact(year), Math.toIntExact(month),1,0,0,0);  // 각월의 첫날짜 (1일)
            LocalDateTime endDate = LocalDateTime.of(Math.toIntExact(year), Math.toIntExact(month), localDate.lengthOfMonth(), 23, 59, 59); // 각월의 마지막날짜(월의 길이)
//            Page<ParkingRecord> pageList = parkingRecordRepository.findByInTimeBetweenOrderByInTime(startDate,endDate,pageable);
            Page<ParkingRecord> pageList;
            if(keyword.isEmpty()) { // 차랑번호 입력 유무
                pageList = parkingRecordRepository.findByInTimeBetweenOrderByInTime(startDate, endDate, pageable);
            } else {
                pageList = parkingRecordRepository.findByInTimeBetweenAndCarNumberContains(startDate, endDate, keyword, pageable);
            }
            return pageList;
        } else {
            // 연, 월, 일 전체입력
            LocalDateTime startDate = LocalDateTime.of(Math.toIntExact(year), Math.toIntExact(month), Math.toIntExact(day), 0, 0, 0);
            LocalDateTime endDate = LocalDateTime.of(Math.toIntExact(year), Math.toIntExact(month), Math.toIntExact(day), 23, 59, 59);
            Page<ParkingRecord> pageList;
            if(keyword.isEmpty()) { // 차랑번호 입력 유무
                pageList = parkingRecordRepository.findByInTimeBetweenOrderByInTime(startDate, endDate, pageable);
            } else {
                pageList = parkingRecordRepository.findByInTimeBetweenAndCarNumberContains(startDate, endDate, keyword, pageable);
            }
            return pageList;
        }
    }
}
