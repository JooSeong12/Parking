package com.example.parkingProject.service;

import com.example.parkingProject.dto.ParkingStateDto;
import com.example.parkingProject.entity.ParkingState;
import com.example.parkingProject.repository.ParkingStateRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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



    public void currentPrice(){ //currentPrice를 계산해서 db에 저장해주는 메서드
        Long currentPrice = 0L;
        List<ParkingState> list = parkingStateRepository.findAll(); //우선 데이터베이스에서 리스트로 모든 값을 가져옴(현재 currentPrice는 null인 상태)
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getCurrentPrice() == null){ //currentPrice가 비어있을 때만 시작
                String patter = "^0[1-9]"; //정규표현식으로 시작이01~09인경우를 찾아준다
                String patter2 = "^1[0-9]+[0-9]"; //정규표현식으로 시작이100~199인경우를 찾아준다
                Pattern pattern = Pattern.compile(patter); //패턴객체생성(정규표현식에서 필요)
                Pattern pattern2 = Pattern.compile(patter2); //패턴객체생성2(정규표현식에서 필요)
                    Matcher matcher = pattern.matcher(list.get(i).getCarNumber()); //매칭해주는 객체
                    Matcher matcher1 = pattern2.matcher(list.get(i).getCarNumber()); // 매칭해주는 객체
                    if(matcher.find() == true || matcher1.find() == true){ //시작이 01~09이거나 100~199인 경우를 가정
                        currentPrice = (long) calculateParkingFee(list.get(i).getInTime(), LocalDateTime.now())/2; //if의 조건이 맞다면 경차이므로 절반가격
                    } else {
                        currentPrice = (long) calculateParkingFee(list.get(i).getInTime(), LocalDateTime.now()); // if의 조건이 맞지 않다면 원래의 가격
                    }
                Long current = currentPrice;
                list.get(i).setCurrentPrice(current); // 구한 주차비를 가져온 list에 저장
                parkingStateRepository.save(list.get(i)); // currentPrice까지 들어간 list를 통해 db로 저장
            }
        }
    }

    public void save(ParkingStateDto dto) {
        ParkingState parkingState1 = ParkingStateDto.fromDto(dto);
        em.persist(parkingState1);
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

    public Page<ParkingState> searchByCarNumber(Pageable pageable,String keyword){
        Page<ParkingState> states = parkingStateRepository.findByCarNumberContains(keyword, pageable);
        return states;
    }

    public ParkingStateDto findById(Long id) {
        ParkingState state = parkingStateRepository.findById(id).orElse(null);
        System.out.println(state);

        return ParkingStateDto.fromEntity(state);
    }
}