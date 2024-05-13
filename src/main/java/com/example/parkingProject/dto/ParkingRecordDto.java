package com.example.parkingProject.dto;

import com.example.parkingProject.entity.ParkingRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingRecordDto {

    private Long registerId;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private float parkingTime;
    private String carNumber;
    private int price;

    public static ParkingRecordDto fromRecordEntity(ParkingRecord pr) {
        return new ParkingRecordDto(
                pr.getRegisterId(), pr.getInTime(), pr.getOutTime(), pr.getParkingTime(), pr.getCarNumber(),pr.getPrice()
        );
    }

}
