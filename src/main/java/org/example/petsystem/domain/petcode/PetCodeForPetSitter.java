package org.example.petsystem.domain.petcode;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.petsitter.PetSitter;

@Entity
@Getter
@DiscriminatorValue("petsitter")
@NoArgsConstructor
public class PetCodeForPetSitter extends PetCode {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_sitter_id")
    private PetSitter petSitter;

    @Builder
    public PetCodeForPetSitter(String name, PetSitter petSitter) {
        super(name);
        this.petSitter = petSitter;
    }
}
