package org.example.petsystem.dto.response.member;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.member.Member;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MypageMemberInfoResponse {

    private String emailAddress;
    private String name;
    private String phoneNumber;

    public static MypageMemberInfoResponse from(Member member) {
        return MypageMemberInfoResponse.builder()
                .emailAddress(member.getEmailAddress())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
