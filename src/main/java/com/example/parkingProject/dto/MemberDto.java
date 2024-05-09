package com.example.parkingProject.dto;

import com.example.parkingProject.constant.MemberShipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
public class MemberDto {

//    private LocalD    ate date;
//    private String membership_end;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate membership_end;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate membership_start;

    //    누적 가격
    private Integer charge;

    private String name;
    private String phone;
    private String carNumber;
    private MemberShipType membershipType;
}
