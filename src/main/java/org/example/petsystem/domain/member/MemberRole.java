package org.example.petsystem.domain.member;

import lombok.Getter;

@Getter
public enum MemberRole {

    ROLE_USER("일반사용자"), ROLE_ADMIN("관리자");

    private String name;

    MemberRole(String name) {
        this.name = name;
    }
}
