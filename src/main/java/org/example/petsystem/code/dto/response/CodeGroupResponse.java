package org.example.petsystem.code.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.code.domain.CodeGroup;

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
