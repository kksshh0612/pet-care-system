package org.example.petsystem.domain.petsitter;

import lombok.Getter;

@Getter
public enum SortType {

    RATING_DESC("평점 내림차순"),
    RATING_ASC("평점 오름차순"),
    FEE_DESC("가격 내림차순"),
    FEE_ASC("가격 오름차순"),
    TOTAL_SERVICE_COUNT_DESC("서비스 횟수 내림차순"),
    TOTAL_SERVICE_COUNT_ASC("서비스 횟수 오름차순");

    private String name;

    SortType(String name) {
        this.name = name;
    }
}
