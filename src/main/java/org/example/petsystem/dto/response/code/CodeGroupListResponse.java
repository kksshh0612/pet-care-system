package org.example.petsystem.dto.response.code;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeGroupListResponse {

    private List<CodeGroupResponse> codeGroups;

    public static CodeGroupListResponse of(List<CodeGroupResponse> codeGroupResponse) {
        return CodeGroupListResponse.builder()
                .codeGroups(codeGroupResponse)
                .build();
    }
}
