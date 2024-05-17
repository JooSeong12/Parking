package com.example.parkingProject.controller;

import com.example.parkingProject.dto.UserAccountDto;
import com.example.parkingProject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserAccountController {
    private final UserService userService;

    public UserAccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("informationUpdate")
    public String signup(UserAccountDto userAccountDto){
        return "informationUpdate";
    }

    @PostMapping("informationUpdate")
    public String signupPost(@Valid UserAccountDto userAccountDto,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "informationUpdate";
        }
        if(! userAccountDto.getPassword1().equals(userAccountDto.getPassword2())){
            bindingResult.rejectValue("password2", "passwordIncorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "informationUpdate";
        }
        try{
            userService.createUser(userAccountDto);
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자 입니다.");
            return "informationUpdate";
        } catch (Exception e){
            bindingResult.reject("signupFailed", e.getMessage());
            return "informationUpdate";
        }

        return "redirect:/";
    }
}
