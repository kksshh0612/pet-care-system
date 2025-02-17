package org.example.petsystem.certification.repository;

import org.example.petsystem.certification.domain.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

}
