package com.example.parkingProject.controller;

import com.example.parkingProject.constant.MembershipType;
import com.example.parkingProject.dto.MemberDto;
import com.example.parkingProject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class ParkingController {
    @Autowired
    MemberService memberService;

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
}