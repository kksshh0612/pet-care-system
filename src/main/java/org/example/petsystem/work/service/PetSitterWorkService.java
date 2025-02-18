package org.example.petsystem.work.service;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.global.exception.CustomException;
import org.example.petsystem.global.exception.ErrorCode;
import org.example.petsystem.petsitter.domain.PetSitter;
import org.example.petsystem.petsitter.repository.PetSitterRepository;
import org.example.petsystem.work.domain.PetSitterWork;
import org.example.petsystem.work.dto.request.PetSitterWorkRegisterRequest;
import org.example.petsystem.work.dto.response.PetSitterWorkListResponse;
import org.example.petsystem.work.dto.response.PetSitterWorkResponse;
import org.example.petsystem.work.repository.PetSitterWorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PetSitterWorkService {

    private final PetSitterWorkRepository petSitterWorkRepository;
    private final PetSitterRepository petSitterRepository;

    /**
     * 펫시터 서비스 등록
     * @param petSitterWorkRegisterRequest
     */
    @Transactional
    public void register(PetSitterWorkRegisterRequest petSitterWorkRegisterRequest){

        PetSitter petSitter = petSitterRepository.findById(petSitterWorkRegisterRequest.getPetSitterId())
                .orElseThrow(() -> new CustomException(ErrorCode.PET_SITTER_NOT_FOUND));

        PetSitterWork petSitterWork = petSitterWorkRegisterRequest.toEntity(petSitter);

        petSitterWorkRepository.save(petSitterWork);
    }

    /**
     * 펫시터 서비스 목록 조회
     * @return
     */
    public PetSitterWorkListResponse findPetSitterWorkList(){

        List<PetSitterWork> petSitterWorks = petSitterWorkRepository.findAll();

        List<PetSitterWorkResponse> petSitterWorkResponses = new ArrayList<>();

        for(PetSitterWork petSitterWork : petSitterWorks){

            PetSitterWorkResponse petSitterWorkResponse = PetSitterWorkResponse.of(petSitterWork.getPetSitter(), petSitterWork);
            petSitterWorkResponses.add(petSitterWorkResponse);
        }

        return new PetSitterWorkListResponse(petSitterWorks.stream()
                .map(petSitterWork -> PetSitterWorkResponse.of(petSitterWork.getPetSitter(), petSitterWork))
                .toList());
    }
}
