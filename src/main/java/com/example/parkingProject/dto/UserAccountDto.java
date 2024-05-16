package com.example.parkingProject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserAccountDto {
    @Size(min=3, max = 15)
    private String userId;
    @NotEmpty(message="비밀번호는 필수 입니다.")
    private String password1;
    @NotEmpty(message="비밀번호 확인은 필수 입니다.")
    private String password2;

}
