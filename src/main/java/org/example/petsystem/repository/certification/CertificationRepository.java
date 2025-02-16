package org.example.petsystem.repository.certification;

import java.util.List;
import org.example.petsystem.domain.certification.Certification;
import org.example.petsystem.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

}
