package org.example.petsystem.repository.code;

import java.util.List;
import org.example.petsystem.domain.code.PetSitterCodeGroup;
import org.example.petsystem.domain.petsitter.PetSitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetSitterCodeGroupRepository extends JpaRepository<PetSitterCodeGroup, Long> {

    List<PetSitterCodeGroup> findAllByPetSitter(PetSitter petSitter);
}
