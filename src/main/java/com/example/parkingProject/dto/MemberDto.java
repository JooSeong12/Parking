package com.example.parkingProject.dto;

import com.example.parkingProject.constant.MembershipType;
import com.example.parkingProject.entity.Membership;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor @NoArgsConstructor
public class MemberDto {
    private Long id;

//    private LocalD    ate date;
//    private String membership_end;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate membershipEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate membershipStart;

    //    누적 가격
    private Integer charge;

    @Size(min = 1,message = "이름을 입력하세요")
    private String name;
    @Size(min = 10,max = 13,message = "전화번호을 입력하세요")
    private String phone;
    @Size(min = 7,max = 9,message = "자동차 번호를 입력하세요")
    private String carNumber;
    @NotNull(message = "차량에 맞는 회원제를 골라주새요")
    private MembershipType membershipType;

    public static MemberDto of(Membership membership) {
        return new MemberDto(
                membership.getMemberId(),
                membership.getMembershipEnd(),
                membership.getMembershipStart(),
                membership.getCharge(),
                membership.getName(),
                membership.getPhone(),
                membership.getCarNumber(),
                membership.getMembershipType()
        );
    }

    public static Membership from(MemberDto dto) {
        Membership membership = new Membership();
        membership.setMemberId(dto.getId());
        membership.setCharge(dto.getCharge());
        membership.setMembershipEnd(dto.membershipEnd);
        membership.setMembershipStart(dto.getMembershipStart());
        membership.setMembershipType(dto.getMembershipType());
        membership.setCarNumber(dto.getCarNumber());
        membership.setName(dto.getName());
        membership.setPhone(dto.getPhone());
        return membership;
    }
}
