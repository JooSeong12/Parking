package com.example.parkingProject.constant;

import lombok.Getter;

@Getter
public enum MemberShipType {
    경차(100000),승용차(200000);
    private final Integer price;

    MemberShipType(Integer price) {
        this.price = price;
    }
}
