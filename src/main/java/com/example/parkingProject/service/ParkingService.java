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

    public List<ParkingStateDto> currentPrice(){ //최종적으로 반환할 List는 dto가 들어간 list
        Long currentPrice = 0L;
        List<ParkingState> list = parkingStateRepository.findAll(); //우선 데이터베이스에서 리스트로 모든 값을 가져옴(현재 currentPrice는 null인 상태)
        List<ParkingStateDto> resultList = new ArrayList<>(); // DTO로 옮길 list를 선언
        for(ParkingState state : list){ // 우선 db에서 가져온 entity의 list를 풀어서 각각의 dto로 변환해주는 과정
            ParkingStateDto parkingStateDto = new ParkingStateDto();
            parkingStateDto.setStateId(state.getStateId());
            parkingStateDto.setCarNumber(state.getCarNumber());
            parkingStateDto.setInTime(state.getInTime());
            resultList.add(parkingStateDto); // dto로 변환한 entity의 값을 DTO의 list에 담아줌(아직까지 currentPrice는 null인 상태)
        }
        for(int i = 0; i<list.size(); i++){
            currentPrice = (long) calculateParkingFee(resultList.get(i).getInTime(), LocalDateTime.now()); // 메서드를 통해 구한 현재주차비를 Long으로 변환해서 선언
            ParkingStateDto parkingStateDto1 = resultList.get(i); //현재 위치의 for문에서 dto를 새롭게 선언하고 새로운 dto를 resultList안에 넣어줌
            Long current = currentPrice;
            parkingStateDto1.setCurrentPrice(current); // 구한 주차비를 새로운 dto에 넣어서 최종적으로 반환해줄 list에 넣어주면 currentPrice까지 들어간 list가 완성
        }
        return resultList;
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

    public void saveDto(){
        for(ParkingStateDto dto : currentPrice()){
            ParkingState entity = new ParkingState();
            entity.setCurrentPrice(dto.getCurrentPrice());
            em.persist(entity);
        }
    }
}