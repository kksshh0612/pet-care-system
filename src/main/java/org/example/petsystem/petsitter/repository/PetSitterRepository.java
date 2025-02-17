package org.example.petsystem.petsitter.repository;

import java.util.Optional;
import org.example.petsystem.member.domain.Member;
import org.example.petsystem.petsitter.domain.PetSitter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetSitterRepository extends JpaRepository<PetSitter, Long> {

    Optional<PetSitter> findByMember(Member member);

    Page<PetSitter> findAll(Pageable pageable);
}
