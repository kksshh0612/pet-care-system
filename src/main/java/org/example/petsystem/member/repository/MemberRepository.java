package org.example.petsystem.member.repository;

import java.util.Optional;
import org.example.petsystem.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAddress(String EmailAddress);
}
