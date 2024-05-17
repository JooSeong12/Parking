package com.example.parkingProject.service;

import com.example.parkingProject.dto.MemberDto;
import com.example.parkingProject.entity.Membership;
import com.example.parkingProject.entity.ParkingState;
import com.example.parkingProject.repository.MembershipRepository;
import groovy.util.logging.Slf4j;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @Transactional @Slf4j
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
        String sql = "SELECT m FROM Membership m " +
                "WHERE m.carNumber = :carNumber " +
                "AND m.membershipEnd >= CURRENT_DATE";
        TypedQuery<ParkingState> query = em.createQuery(sql,ParkingState.class)
                .setParameter("carNumber",carNumber);

        try {
            query.getSingleResult();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
