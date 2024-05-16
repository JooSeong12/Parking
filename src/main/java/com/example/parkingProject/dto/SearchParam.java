package com.example.parkingProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParam {
    private Long year;
    private Long month;
    private Long day;
    private String carNumber;
}
