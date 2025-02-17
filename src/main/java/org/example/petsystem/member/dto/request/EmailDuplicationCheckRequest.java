package org.example.petsystem.member.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDuplicationCheckRequest {

    @NotNull(message = "이메일 주소 입력은 필수입니다.")
    private String emailAddress;
}
