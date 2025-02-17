package org.example.petsystem.code.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeDetailListResponse {

    private List<CodeDetailResponse> codeDetailResponses;

    public static CodeDetailListResponse of(List<CodeDetailResponse> codeDetailResponses) {
        return CodeDetailListResponse.builder()
                .codeDetailResponses(codeDetailResponses)
                .build();
    }
}
