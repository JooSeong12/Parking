package com.example.parkingProject.service;

import com.example.parkingProject.Repository.ParkingRecordRepository;
import com.example.parkingProject.dto.ParkingRecordDto;
import com.example.parkingProject.entity.ParkingRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public List<ParkingRecordDto> searchRecord(String year) {
        List<ParkingRecordDto> dtoList = new ArrayList<>();
        dtoList = parkingRecordRepository.searchRecord(year)
                .stream()
                .map(x->ParkingRecordDto.fromRecordEntity(x))
                .toList();
        return dtoList;
    }
}
