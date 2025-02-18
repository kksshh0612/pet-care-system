package org.example.petsystem.work.domain;

import lombok.Getter;

@Getter
public enum PetSize {

    SMALL("소형견"), MEDIUM("중형견"), LARGE("대형견");

    private String name;

    PetSize(String name) {
        this.name = name;
    }
}
