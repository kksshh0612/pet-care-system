package org.example.petsystem.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.member.domain.Member;

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
