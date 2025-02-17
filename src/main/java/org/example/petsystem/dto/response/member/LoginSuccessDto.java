package org.example.petsystem.dto.response.member;

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
public class LoginSuccessDto {

    private Long memberId;
    private MemberRole memberRole;

    public static LoginSuccessDto of(Member member) {
        return LoginSuccessDto.builder()
                .memberId(member.getId())
                .memberRole(member.getMemberRole())
                .build();
    }
}
