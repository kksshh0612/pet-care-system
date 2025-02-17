package org.example.petsystem.repository.code;

import org.example.petsystem.domain.code.CodeDetail;
import org.example.petsystem.domain.code.CodeGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, Long> {

    Page<CodeDetail> findAllByCodeGroup(CodeGroup codeGroup, Pageable pageable);
}
