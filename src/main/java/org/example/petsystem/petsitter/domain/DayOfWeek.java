package org.example.petsystem.petsitter.domain;

import lombok.Getter;

@Getter
public enum DayOfWeek {

    MON("월"), TUE("화"), WED("수"), THU("목"), FRI("금"), SAT("토"), SUN("일");

    private String name;

    DayOfWeek(String name) {
        this.name = name;
    }
}
