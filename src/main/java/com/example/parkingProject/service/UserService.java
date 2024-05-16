package com.example.parkingProject.service;

import com.example.parkingProject.dto.UserAccountDto;
import com.example.parkingProject.entity.UserAccount;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EntityManager em;
    @Transactional
    public void createUser(UserAccountDto userAccountDto) {
        UserAccount account = new UserAccount();
        account.setUserId(userAccountDto.getUserId());
        account.setUserPassword(passwordEncoder.encode(
                userAccountDto.getPassword1()
        ));
        em.persist(account);
    }
}
