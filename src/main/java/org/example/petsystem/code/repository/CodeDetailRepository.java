package org.example.petsystem.code.repository;

import org.example.petsystem.code.domain.CodeDetail;
import org.example.petsystem.code.domain.CodeGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, Long> {

    Page<CodeDetail> findAllByCodeGroup(CodeGroup codeGroup, Pageable pageable);
}
