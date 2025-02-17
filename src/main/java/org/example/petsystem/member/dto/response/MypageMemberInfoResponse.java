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
public class MypageMemberInfoResponse {

    private String emailAddress;
    private String name;
    private String phoneNumber;
    private boolean isAdmin;

    public static MypageMemberInfoResponse from(Member member) {

        return MypageMemberInfoResponse.builder()
                .emailAddress(member.getEmailAddress())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .isAdmin(member.getMemberRole().equals(MemberRole.ROLE_ADMIN))
                .build();
    }
}
