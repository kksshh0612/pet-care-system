package org.example.petsystem.work.domain;

import lombok.Getter;

@Getter
public enum ServiceType {

    WALK("산책"), VISIT_CARE("방문 돌봄"), FOSTER_CARE("위탁 돌봄");

    private String name;

    ServiceType(String name) {
        this.name = name;
    }
}
