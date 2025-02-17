package org.example.petsystem.dto.request.code;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CodeGroupModifyRequest {

    @NotNull(message = "필수 입력값입니다.")
    private Long id;
    @NotNull(message = "필수 입력값입니다.")
    private String code;
    @NotNull(message = "필수 입력값입니다.")
    private String groupName;
    @NotNull(message = "필수 입력값입니다.")
    private String description;
}
