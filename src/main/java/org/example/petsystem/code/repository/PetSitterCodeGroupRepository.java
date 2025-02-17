package org.example.petsystem.code.repository;

import java.util.List;
import org.example.petsystem.code.domain.PetSitterCodeGroup;
import org.example.petsystem.petsitter.domain.PetSitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetSitterCodeGroupRepository extends JpaRepository<PetSitterCodeGroup, Long> {

    List<PetSitterCodeGroup> findAllByPetSitter(PetSitter petSitter);
}
