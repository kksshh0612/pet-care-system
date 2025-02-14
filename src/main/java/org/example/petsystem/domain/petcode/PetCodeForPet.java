package org.example.petsystem.domain.petcode;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.pet.Pet;

@Entity
@Getter
@DiscriminatorValue("pet")
@NoArgsConstructor
public class PetCodeForPet extends PetCode {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Builder
    public PetCodeForPet(String name, Pet pet) {
        super(name);
        this.pet = pet;
    }
}
