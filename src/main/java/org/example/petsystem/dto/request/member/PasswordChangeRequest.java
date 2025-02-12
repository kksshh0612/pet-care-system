package org.example.petsystem.dto.request.member;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeRequest {

    @NotNull(message = "기존 비밀번호 입력은 필수입니다.")
    private String oldPassword;
    @NotNull(message = "새로운 비밀번호 입력은 필수입니다.")
    private String newPassword;
}
