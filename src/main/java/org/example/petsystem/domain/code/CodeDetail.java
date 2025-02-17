package org.example.petsystem.domain.code;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.global.auditing.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class CodeDetail extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_detail_id")
    private Long id;

    private String codeName;

    private String codeValue;

    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_group_id")
    private CodeGroup codeGroup;

    @Builder
    public CodeDetail(String codeName, String codeValue, boolean isActive, CodeGroup codeGroup) {
        this.codeName = codeName;
        this.codeValue = codeValue;
        this.isActive = isActive;
        this.codeGroup = codeGroup;
    }
}
