package com.example.parkingProject.controller;


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
import java.util.List;

@Controller
@Slf4j
public class ParkingController {
    @Autowired
    EntityManager em;

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

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

        return "parkingState";
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


}
