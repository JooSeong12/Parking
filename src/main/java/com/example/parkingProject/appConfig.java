package com.example.parkingProject;

import com.example.parkingProject.entity.ParkingState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //오류 생겨서 따로 만든클래스(의존성 문제)
public class appConfig {

    @Bean
    public ParkingState parkingState() {
        return new ParkingState();
    }
}