package com.example.parkingProject.dto;

import com.example.parkingProject.entity.ParkingRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingRecordDto {

    private Long registerId;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private Long parkingTime;
    private String carNumber;
    private Integer price;
    private Integer finalPrice;
    private String vigo;

    public static ParkingRecordDto fromRecordEntity(ParkingRecord pr) {
        return new ParkingRecordDto(
                pr.getRegisterId(), pr.getInTime(), pr.getOutTime(), pr.getParkingTime(), pr.getCarNumber(),pr.getPrice(), pr.getFinalPrice(), pr.getVigo()
        );
    }

    public static ParkingRecordDto fromState(ParkingStateDto state){
        ParkingRecordDto dto = new ParkingRecordDto();
        dto.setInTime(state.getInTime());
        dto.setCarNumber(state.getCarNumber());
        return dto;
    }

    public static ParkingRecord fromDto(ParkingRecordDto dto) {
        return new ParkingRecord(
                dto.getRegisterId(), dto.getInTime(), dto.getOutTime(), Duration.between(dto.getInTime(), dto.getOutTime()).toMinutes(), dto.getCarNumber(),dto.getPrice(), dto.getFinalPrice(), dto.getVigo()
        );
    }
}
