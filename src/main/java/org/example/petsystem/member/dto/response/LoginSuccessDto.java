package org.example.petsystem.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.member.domain.Member;
import org.example.petsystem.member.domain.MemberRole;

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
