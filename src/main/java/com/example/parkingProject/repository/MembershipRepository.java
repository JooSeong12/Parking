package com.example.parkingProject.repository;

import com.example.parkingProject.entity.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Page<Membership> findByNameContains(String keyword, Pageable pageable);

    Page<Membership> findByPhoneContains(String keyword, Pageable pageable);

    Page<Membership> findByCarNumberContains(String keyword, Pageable pageable);

    Membership findByCarNumber(String carNumber);
}
