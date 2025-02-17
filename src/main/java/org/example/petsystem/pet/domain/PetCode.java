package org.example.petsystem.pet.domain;

import lombok.Getter;

@Getter
public enum PetCode {

    DOG("개"), CAT("고양이"), RABBIT("토끼"), HAMSTER("햄스터"), PARROT("앵무새");

    private String name;

    PetCode(String name) {
        this.name = name;
    }
}
