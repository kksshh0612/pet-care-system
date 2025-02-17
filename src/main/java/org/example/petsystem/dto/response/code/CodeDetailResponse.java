package org.example.petsystem.dto.response.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.code.CodeDetail;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeDetailResponse {

    private Long id;
    private String codeName;
    private String codeValue;
    private boolean isActive;

    public static CodeDetailResponse of(CodeDetail codeDetail) {
        return CodeDetailResponse.builder()
                .id(codeDetail.getId())
                .codeName(codeDetail.getCodeName())
                .codeValue(codeDetail.getCodeValue())
                .isActive(codeDetail.isActive())
                .build();
    }
}
