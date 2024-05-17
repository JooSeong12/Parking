package com.example.parkingProject.dto;

import com.example.parkingProject.entity.ParkingState;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingStateDto {
    private Long stateId; //프라이머리 키
    @Size(min = 7,max = 9,message = "차량 번호를 입력하세요")
    private String carNumber; //차량번호
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime inTime; // 입차시간
    private Long currentPrice; // 현재까지 주차비(java에서 구현해야함)

    public static ParkingStateDto fromEntity(ParkingState parkingState){
        return new ParkingStateDto(parkingState.getStateId(), parkingState.getCarNumber(), parkingState.getInTime(), parkingState.getCurrentPrice());
    }

    public static ParkingState fromDto(ParkingStateDto parkingStateDto){
        ParkingState parkingState = new ParkingState();
        parkingState.setStateId(parkingStateDto.getStateId());
        parkingState.setCarNumber(parkingStateDto.getCarNumber());
        parkingState.setInTime(parkingStateDto.getInTime());
        parkingState.setCurrentPrice(parkingStateDto.getCurrentPrice());
        return parkingState;
    }
}