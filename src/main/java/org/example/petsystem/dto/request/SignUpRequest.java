package org.example.petsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.member.Member;
import org.example.petsystem.domain.member.MemberRole;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequest {

    private String emailAddress;
    private String password;
    private String name;
    private String phoneNumber;

    public Member toEntity(String encodedPassword){
        return Member.builder()
                .emailAddress(this.emailAddress)
                .password(encodedPassword)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .memberRole(MemberRole.ROLE_USER)
                .activeStatus(true)
                .build();
    }
}
