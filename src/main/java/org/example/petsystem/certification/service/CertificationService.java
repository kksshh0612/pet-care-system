package org.example.petsystem.certification.service;

import lombok.RequiredArgsConstructor;
import org.example.petsystem.certification.domain.Certification;
import org.example.petsystem.certification.dto.request.CertificationSaveRequest;
import org.example.petsystem.certification.repository.CertificationRepository;
import org.example.petsystem.file.domain.CertificationFile;
import org.example.petsystem.file.repository.CertificationFileRepository;
import org.example.petsystem.global.exception.CustomException;
import org.example.petsystem.global.exception.ErrorCode;
import org.example.petsystem.petsitter.domain.PetSitter;
import org.example.petsystem.petsitter.repository.PetSitterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final PetSitterRepository petSitterRepository;
    private final CertificationFileRepository certificationFileRepository;

    @Transactional
    public void save(CertificationSaveRequest saveRequest, MultipartFile multipartFile) {

        PetSitter petSitter = petSitterRepository.findById(saveRequest.getPetSitterId())
                .orElseThrow(() -> new CustomException(ErrorCode.PET_SITTER_NOT_FOUND));

        Certification certification = Certification.builder()
                .name(saveRequest.getName())
                .petSitter(petSitter)
                .build();

    }
}
