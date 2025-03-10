package org.example.petsystem.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.global.auditing.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String emailAddress;

    private String password;

    private String name;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    private boolean activeStatus;

    @Builder
    public Member(String emailAddress, String password, String name, String phoneNumber, MemberRole memberRole,
                  boolean activeStatus) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.memberRole = memberRole;
        this.activeStatus = activeStatus;
    }

    //== 비지니스 로직 ==//
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void modify(String emailAddress, String name, String phoneNumber){
        this.emailAddress = emailAddress;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
