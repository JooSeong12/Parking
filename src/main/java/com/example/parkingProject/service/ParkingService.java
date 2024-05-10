package com.example.parkingProject.service;

import com.example.parkingProject.dto.ParkingStateDto;
import com.example.parkingProject.entity.ParkingState;
import com.example.parkingProject.repository.ParkingStateRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@Transactional
public class ParkingService {
    @Autowired
    EntityManager em;
    private final ParkingState parkingState;
    private final ParkingStateRepository parkingStateRepository;

    public ParkingService(ParkingState parkingState, ParkingStateRepository parkingStateRepository) {
        this.parkingState = parkingState;
        this.parkingStateRepository = parkingStateRepository;
    }

    public void currentPrice(){ //최종적으로 반환할 List는 dto가 들어간 list
        Long currentPrice = 0L;
        List<ParkingState> list = parkingStateRepository.findAll(); //우선 데이터베이스에서 리스트로 모든 값을 가져옴(현재 currentPrice는 null인 상태)
        for(int i = 0; i<list.size(); i++){
            currentPrice = (long) calculateParkingFee(list.get(i).getInTime(), LocalDateTime.now()); // 메서드를 통해 구한 현재주차비를 Long으로 변환해서 선언
            Long current = currentPrice;
            list.get(i).setCurrentPrice(current); // 구한 주차비를 새로운 dto에 넣어서 최종적으로 반환해줄 list에 넣어주면 currentPrice까지 들어간 list가 완성
            System.out.println(list.toString());
            parkingStateRepository.save(list.get(i));
        }
    }

    public int calculateParkingFee(LocalDateTime entranceTime, LocalDateTime exitTime) { //주차비 구하는 메서드
        // 시간 간격 계산
        Duration duration = Duration.between(entranceTime, exitTime); //입차, 출차 시간의 차이를 구함
        long minutes = duration.toMinutes(); // 구한 차이값을 long으로 변환

        // 10분당 1000원 요금 적용
        int feePer10Minutes = 1000; // 10분당 1000원을 선언
        int parkingFee = (int) (minutes / 10) * feePer10Minutes; //위에서 구한 차이값을 1000원으로 곱해서 현재 주차비 구함

        return parkingFee;
    }


    public Page<ParkingState> pagingList(Pageable pageable) {
        return parkingStateRepository.findAll(pageable);
    }

    private static final int BAR_LENGTH = 5;

    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages){
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH/2), 0);
        int endNumber = Math.min(startNumber+BAR_LENGTH, totalPages);
        return IntStream.range(startNumber, endNumber).boxed().toList();
    }
}