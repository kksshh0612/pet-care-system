package org.example.petsystem.repository.petsitter;

import java.util.Optional;
import org.example.petsystem.domain.member.Member;
import org.example.petsystem.domain.petsitter.PetSitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetSitterRepository extends JpaRepository<PetSitter, Long> {

    Optional<PetSitter> findByMember(Member member);
}
