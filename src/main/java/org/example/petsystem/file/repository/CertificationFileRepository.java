package org.example.petsystem.file.repository;

import org.example.petsystem.file.domain.CertificationFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationFileRepository extends JpaRepository<CertificationFile, Long> {
}
