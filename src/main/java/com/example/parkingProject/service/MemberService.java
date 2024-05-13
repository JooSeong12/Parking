package com.example.parkingProject.service;

import com.example.parkingProject.dto.MemberDto;
import com.example.parkingProject.entity.Membership;
import com.example.parkingProject.repository.MembershipRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @Transactional
public class MemberService {
    @Autowired
    EntityManager em;

    @Autowired
    MembershipRepository membershipRepository;

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

    public Page<Membership> pagingList(Pageable pageable) {
        return membershipRepository.findAll(pageable);
    }

    public Page<Membership> searchByName(String keyword, Pageable pageable) {
        Page<Membership> nameList = membershipRepository.findByNameContains(keyword, pageable);
        return nameList;
    }

    public Page<Membership> searchByPhone(String keyword, Pageable pageable) {
        Page<Membership> phoneList = membershipRepository.findByPhoneContains(keyword, pageable);
        return phoneList;
    }

    public Page<Membership> searchByCarNumber(String keyword, Pageable pageable) {
        Page<Membership> carNumberList = membershipRepository.findByCarNumberContains(keyword, pageable);
        return carNumberList;
    }

    public boolean findByCarNumber(String carNumber) {
        Membership membership = membershipRepository.findByCarNumber(carNumber);
        if ( membership != null){
            return true;
        }
        else return false;
    }
}
