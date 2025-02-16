package org.example.petsystem.domain.file;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.certification.Certification;
import org.example.petsystem.domain.member.Member;
import org.example.petsystem.domain.petsitter.PetSitter;

@Entity
@Getter
@DiscriminatorValue("certi")
@NoArgsConstructor
public class CertificationFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_sitter_id")
    private PetSitter petSitter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @Builder
    public CertificationFile(String url, PetSitter petSitter, Certification certification) {
        super(url);
        this.petSitter = petSitter;
        this.certification = certification;
    }
}
