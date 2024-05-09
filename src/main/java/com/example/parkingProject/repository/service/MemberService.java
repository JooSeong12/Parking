package com.example.parkingProject.repository.service;

import com.example.parkingProject.dto.MemberDto;
import com.example.parkingProject.entity.Membership;
import com.example.parkingProject.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MembershipRepository repo;

    public MemberDto findById(Long id){
        Membership membership = repo.findById(id).orElse(null);
        return MemberDto.of(membership);
    }

    public void save(MemberDto dto) {
        System.out.println(dto);
        repo.save(
                MemberDto.from(dto)
        );
    }
}
