package com.example.parkingProject.constant;

import lombok.Getter;

@Getter
public enum MembershipType {
    경차(100000),승용차(200000);
    private final Integer price;

    MembershipType(Integer price) {
        this.price = price;
    }
}
