package org.example.petsystem.repository.member;

import java.util.Optional;
import org.example.petsystem.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAddress(String EmailAddress);
}
