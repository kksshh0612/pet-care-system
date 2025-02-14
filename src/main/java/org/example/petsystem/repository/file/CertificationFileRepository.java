package org.example.petsystem.repository.file;

import org.example.petsystem.domain.certification.Certification;
import org.example.petsystem.domain.file.CertificationFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationFileRepository extends JpaRepository<CertificationFile, Long> {
}
