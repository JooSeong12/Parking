package com.example.parkingProject.controller;


import com.example.parkingProject.dto.ParkingRecordDto;
import com.example.parkingProject.dto.ParkingStateDto;
import com.example.parkingProject.entity.ParkingState;
import com.example.parkingProject.service.ParkingService;
import jakarta.persistence.EntityManager;

import com.example.parkingProject.constant.MembershipType;
import com.example.parkingProject.dto.MemberDto;
import com.example.parkingProject.entity.Membership;
import com.example.parkingProject.service.MemberService;
import com.example.parkingProject.service.PaginationService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@Slf4j
public class ParkingController {
    @Autowired
    EntityManager em;

    @Autowired
    ParkingService parkingService;

    @GetMapping("parkingState")
    public String parkingState(Model model,
                               @PageableDefault(page = 0, size = 10, sort = "stateId",
                                       direction = Sort.Direction.ASC) Pageable pageable){
        parkingService.currentPrice(); //currentPrice를 먼저 db에 저장해준 후 시작
        Page<ParkingState> paging = parkingService.pagingList(pageable);
        int totalPage = paging.getTotalPages();
        List<Integer> barNumbers = parkingService.getPaginationBarNumbers(pageable.getPageNumber(), totalPage);
        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("paging", paging);

        return "parking/parkingState";
    }

    @GetMapping("parkingState/search")
    public String parkingStateSearch(@RequestParam("keyword")String keyword,
                                     @RequestParam("searchType")String type,
                                     Model model,
                                     @PageableDefault(page = 0, size = 10, sort = "stateId",
                                             direction = Sort.Direction.DESC) Pageable pageable){
        Page<ParkingState> searchList = null;
        List<Integer> barNumbers = null;
        if(type.equals("carNumber")){
            searchList = parkingService.searchByCarNumber(pageable,keyword);
            int totalPage = searchList.getTotalPages();
            barNumbers = parkingService.getPaginationBarNumbers(pageable.getPageNumber(), totalPage);
        }
        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("paging", searchList);
        //서칭+페이징을 위해 받아온 키워드와 검색타입도 넘김
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchType", type);
        return "parking/parkingState";
    }


    @Autowired
    MemberService memberService;
    @Autowired
    PaginationService paginationService;

    @GetMapping("/updateMember")
    private String update(@RequestParam("id")Long id, Model model){
        MemberDto dto = memberService.findById(id);

        model.addAttribute("membershipType", MembershipType.values());
        model.addAttribute("dto", dto );

        return "member/update";
    }
    @PostMapping("/updateMember")
    private String update(@ModelAttribute("dto") MemberDto dto){
        memberService.update(dto);
        return "redirect:/";
    }

    @GetMapping("/insertMember")
    private String signUp(Model model){
        MemberDto dto = new MemberDto();

        dto.setMembershipStart(LocalDateTime.now().toLocalDate());
        dto.setMembershipEnd(LocalDateTime.now().toLocalDate().plusMonths(1));

        model.addAttribute("dto", dto);
        model.addAttribute("membershipType", MembershipType.values());

        return "member/signUp";
    }

    @PostMapping("/insertMember")
    private String signUp(@ModelAttribute("dto") MemberDto dto){
        memberService.insert(dto);
        return "redirect:/";
    }

    @GetMapping("/")
    public String main(){
        return "main";
    }


    //회원 조회 페이지
    @GetMapping("viewMember")
    public String memberView(Model model,
                             @PageableDefault(page = 0, size = 15, sort = "memberId",
                                     //membershipNum로 정렬
                                     direction = Sort.Direction.DESC) Pageable pageable) {
        // 넘겨온 페이지 번호로 리스트 받아오기
        Page<Membership> paging = memberService.pagingList(pageable);

        // 페이지 블럭 처리(1, 2, 3, 4, 5)
        int totalPage = paging.getTotalPages();
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(
                pageable.getPageNumber(), totalPage);

        model.addAttribute("paginationBarNumbers", barNumbers);
        model.addAttribute("paging", paging);
        return "member/member_view";
    }

    //회원 조회 - 검색 페이지
    @GetMapping("viewMember/search")
    public String search(@RequestParam("keyword")String keyword,
                         @RequestParam("searchType")String type,
                         Model model,
                         @PageableDefault(page = 0, size = 10, sort = "memberId",
                                 direction = Sort.Direction.DESC) Pageable pageable){

        if(type.equals("name")){
            //받아온 키워드 포함 이름 검색
            Page<Membership> searchList = memberService.searchByName(keyword, pageable);

            //페이지 블럭 처리
            int totalPage = searchList.getTotalPages();
            List<Integer> barNumbers = paginationService.getPaginationBarNumbers(
                    pageable.getPageNumber(), totalPage);

            model.addAttribute("paginationBarNumbers", barNumbers);
            model.addAttribute("searchList", searchList);
            //서칭+페이징을 위해 받아온 키워드와 검색타입도 넘김
            model.addAttribute("keyword", keyword);
            model.addAttribute("searchType", type);
        } else if (type.equals("phone")) {
            //받아온 키워드 포함 번호 검색
            Page<Membership> searchList = memberService.searchByPhone(keyword, pageable);

            //페이지 블럭 처리
            int totalPage = searchList.getTotalPages();
            List<Integer> barNumbers = paginationService.getPaginationBarNumbers(
                    pageable.getPageNumber(), totalPage);

            model.addAttribute("paginationBarNumbers", barNumbers);
            model.addAttribute("searchList", searchList);
            //서칭+페이징을 위해 받아온 키워드와 검색타입도 넘김
            model.addAttribute("keyword", keyword);
            model.addAttribute("searchType", type);
        }else {
            //받아온 키워드 포함 차 번호 검색
            Page<Membership> searchList = memberService.searchByCarNumber(keyword, pageable);

            //페이지 블럭 처리
            int totalPage = searchList.getTotalPages();
            List<Integer> barNumbers = paginationService.getPaginationBarNumbers(
                    pageable.getPageNumber(), totalPage);

            model.addAttribute("paginationBarNumbers", barNumbers);
            model.addAttribute("searchList", searchList);
            //서칭+페이징을 위해 받아온 키워드와 검색타입도 넘김
            model.addAttribute("keyword", keyword);
            model.addAttribute("searchType", type);
        }
        return "member/search_view";
    }

    @GetMapping("parking")
    public String parking(Model model){
        model.addAttribute("dto",new ParkingStateDto());
        return "parking/parking";
    }

    @PostMapping("/parking")
    public String parking(@ModelAttribute("dto") ParkingStateDto dto) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime truncatedTime = now.truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
        dto.setInTime(truncatedTime);
        parkingService.save(dto);
        return "redirect:/";
    }

    @GetMapping("/payment")
    public String payment(@RequestParam("id")Long id, Model model){
        ParkingStateDto state = parkingService.findById(id);
        ParkingRecordDto dto = ParkingRecordDto.fromState(state);
        Integer price = parkingService.calculateParkingFee(dto.getInTime(),LocalDateTime.now());
        dto.setPrice(price);
        try {
            if (Integer.parseInt(dto.getCarNumber().substring(0,2)) <= 9){
                price = price/2;
            } else if (Integer.parseInt(dto.getCarNumber().substring(0,3)) >= 100){
                if (Integer.parseInt(dto.getCarNumber().substring(0,3)) <= 199){
                    price = price/2;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        dto.setFinalPrice(price);
        model.addAttribute("dto",dto);
        boolean member = memberService.findByCarNumber(dto.getCarNumber());
        model.addAttribute("member",member);
        System.out.println(dto);
        return "parking/payment";
    }

    @PostMapping("/payment")
    public String payment(@ModelAttribute("dto")ParkingRecordDto dto){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime truncatedTime = now.truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
        dto.setOutTime(truncatedTime);
        System.out.println(dto);
        parkingService.payment(dto);
        return "redirect:/";
    }
}

