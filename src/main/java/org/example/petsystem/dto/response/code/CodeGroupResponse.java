package org.example.petsystem.dto.response.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.code.CodeGroup;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeGroupResponse {

    private Long id;
    private String code;
    private String groupName;
    private String description;

    public static CodeGroupResponse from(CodeGroup codeGroup) {
        return CodeGroupResponse.builder()
                .id(codeGroup.getId())
                .code(codeGroup.getCode())
                .groupName(codeGroup.getGroupName())
                .description(codeGroup.getDescription())
                .build();
    }
}
