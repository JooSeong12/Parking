package com.example.parkingProject.service;

import com.example.parkingProject.repository.MembershipRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest @Transactional
class MemberServiceTest {
    @Autowired
    EntityManager em;

    @Test
    public void carNumbers(){
        String sql = "SELECT m.carNumber FROM Membership m";
        TypedQuery<String> query = em.createQuery(sql,String.class);
        System.out.println(query.getResultList());
    }


}