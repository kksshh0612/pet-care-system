package org.example.petsystem.dto.request.code;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.code.CodeDetail;
import org.example.petsystem.domain.code.CodeGroup;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeDetailRegisterRequest {

    @NotNull(message = "필수 입력값입니다.")
    private String codeName;
    @NotNull(message = "필수 입력값입니다.")
    private String codeValue;
    @NotNull(message = "필수 입력값입니다.")
    private Long codeGroupId;

    public CodeDetail toEntity(CodeGroup codeGroup) {
        return CodeDetail.builder()
                .codeName(codeName)
                .codeValue(codeValue)
                .codeGroup(codeGroup)
                .isActive(true)
                .build();
    }
}
