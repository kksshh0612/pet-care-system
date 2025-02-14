package org.example.petsystem.domain.petcode;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor
public abstract class PetCode {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_code_id")
    private Long id;

    private String name;

    public PetCode(String name) {
        this.name = name;
    }
}
