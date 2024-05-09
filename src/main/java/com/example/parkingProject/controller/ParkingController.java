package com.example.parkingProject.controller;

import com.example.parkingProject.constant.MemberShipType;
import com.example.parkingProject.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class ParkingController {
    @GetMapping("/updateMember")
    private String update(Model model){
        MemberDto dto = new MemberDto(LocalDateTime.now().toLocalDate(),LocalDateTime.now().minusMonths(1).toLocalDate(),100000,"null","010-0000-0000","72가20202", MemberShipType.경차);

        model.addAttribute("membershipType", MemberShipType.values());
        model.addAttribute("dto", dto );

        return "member/update";
    }
    @PostMapping("/updateMember")
    private String update(@ModelAttribute("dto") MemberDto dto){
        return "redirect:/";
    }

    @GetMapping("/insertMember")
    private String signUp(Model model){
        MemberDto dto = new MemberDto();

        dto.setMembership_start(LocalDateTime.now().toLocalDate());
        dto.setMembership_end(LocalDateTime.now().toLocalDate().plusMonths(1));

        model.addAttribute("dto", dto);
        model.addAttribute("membershipType",MemberShipType.values());

        return "member/signUp";
    }
    @PostMapping("/insertMember")
    private String signUp(@ModelAttribute("dto") MemberDto dto){
        return "redirect:/";
    }

    @GetMapping("/")
    public String main(){
        return "main";
    }
}