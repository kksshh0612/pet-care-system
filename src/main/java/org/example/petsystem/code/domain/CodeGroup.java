package org.example.petsystem.code.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.global.auditing.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class CodeGroup extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_group_id")
    private Long id;

    private String code;

    private String groupName;

    private String description;

    @Builder
    public CodeGroup(String code, String groupName, String description) {
        this.code = code;
        this.groupName = groupName;
        this.description = description;
    }

    //== 비지니스 로직 ==//

    public void modify(String code, String groupName, String description) {
        this.code = code;
        this.groupName = groupName;
        this.description = description;
    }
}
