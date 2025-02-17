package org.example.petsystem.file.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.pet.domain.Pet;

@Entity
@Getter
@DiscriminatorValue("pet")
@NoArgsConstructor
public class PetImageFile extends File{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public PetImageFile(String url, Pet pet) {
        super(url);
        this.pet = pet;
    }
}
