package com.example.parkingProject.service;

import com.example.parkingProject.dto.MemberDto;
import com.example.parkingProject.entity.Membership;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service @Transactional
public class MemberService {
    @Autowired
    EntityManager em;

    public MemberDto findById(Long id){
        Membership membership = em.find(Membership.class,id);
        return MemberDto.of(membership);
    }

    public void insert(MemberDto dto) {
        Membership membership = MemberDto.from(dto);
        em.persist(membership);
    }
    public void update(MemberDto dto) {
        Membership membership = MemberDto.from(dto);
        em.merge(membership);
    }
}
