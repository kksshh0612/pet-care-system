package org.example.petsystem.dto.request.code;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.code.CodeGroup;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CodeGroupRegisterRequest {

    @NotNull(message = "필수 입력값입니다.")
    private String code;
    @NotNull(message = "필수 입력값입니다.")
    private String groupName;
    @NotNull(message = "필수 입력값입니다.")
    private String description;

    public CodeGroup toEntity(){
        return CodeGroup.builder()
                .code(code)
                .groupName(groupName)
                .description(description)
                .build();
    }
}
