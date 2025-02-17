package org.example.petsystem.member.dto.request;

import jakarta.validation.constraints.NotNull;
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
public class SignUpRequest {

    @NotNull(message = "이메일 주소 입력은 필수입니다.")
    private String emailAddress;
    @NotNull(message = "비밀번호 입력은 필수입니다.")
    private String password;
    @NotNull(message = "이름 입력은 필수입니다.")
    private String name;
    @NotNull(message = "전화번호 입력은 필수입니다.")
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
