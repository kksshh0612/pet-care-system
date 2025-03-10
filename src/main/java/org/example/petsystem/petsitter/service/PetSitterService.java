package org.example.petsystem.petsitter.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.global.exception.CustomException;
import org.example.petsystem.global.exception.ErrorCode;
import org.example.petsystem.member.domain.Member;
import org.example.petsystem.pet.domain.Pet;
import org.example.petsystem.petsitter.domain.PetSitter;
import org.example.petsystem.petsitter.domain.SortType;
import org.example.petsystem.petsitter.dto.request.PetSitterProfileModifyRequest;
import org.example.petsystem.petsitter.dto.request.PetSitterRegisterRequest;
import org.example.petsystem.petsitter.dto.response.PetSitterProfileListResponse;
import org.example.petsystem.petsitter.dto.response.PetSitterProfileResponse;
import org.example.petsystem.member.repository.MemberRepository;
import org.example.petsystem.petsitter.repository.PetSitterRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PetSitterService {

    private final PetSitterRepository petSitterRepository;
    private final MemberRepository memberRepository;

    /**
     * 펫시터 프로필 등록
     * @param memberId
     * @param petSitterRegisterRequest
     */
    @Transactional
    public void register(Long memberId, PetSitterRegisterRequest petSitterRegisterRequest){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        if(petSitterRepository.findByMember(member).isPresent()){
            throw new CustomException(ErrorCode.PET_SITTER_ALREADY_REGISTERED);
        }

        PetSitter petSitter = petSitterRegisterRequest.toEntity(member);
        petSitterRepository.save(petSitter);
    }

    /**
     * 회원 펫시터 프로필 단건 조회
     * @param petSitterId
     * @return
     */
    public PetSitterProfileResponse findPetSitterProfile(Long petSitterId){

        PetSitter petSitter = petSitterRepository.findById(petSitterId)
                .orElseThrow(() -> new CustomException(ErrorCode.PET_SITTER_NOT_FOUND));

        return  PetSitterProfileResponse.of(petSitter, petSitter.getCertifications());
    }

    /**
     * 마이페이지 회원 펫시터 프로필 단건 조회
     * @param memberId
     * @return
     */
    public PetSitterProfileResponse findMyPetSitterProfile(Long memberId){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PetSitter petSitter = petSitterRepository.findByMember(member)
                .orElseThrow(() -> new CustomException(ErrorCode.PET_SITTER_NOT_FOUND));

        return  PetSitterProfileResponse.of(petSitter, petSitter.getCertifications());
    }

    /**
     * 회원 펫시터 프로필 수정
     * @param memberId
     * @param modifyRequest
     */
    public void modifyPetSitterProfile(Long memberId, PetSitterProfileModifyRequest modifyRequest){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        System.out.println(modifyRequest.getId());
        System.out.println(modifyRequest.getFee());
        System.out.println(modifyRequest.getIntroduction());

        PetSitter petSitter = petSitterRepository.findById(modifyRequest.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.PET_SITTER_NOT_FOUND));

        petSitter.modify(modifyRequest.getLocation(), modifyRequest.getAvailableDaysOfWeek(),
                modifyRequest.getFee(), modifyRequest.getIntroduction());
    }

    /**
     * 펫시터 프로필 목록 조회
     * @param page
     * @param size
     * @param sortType
     * @return
     */
    public PetSitterProfileListResponse findPetSitterProfileListForManager(int page, int size){

//        PageRequest pageRequest;
//
//        switch (sortType){
//            case RATING_DESC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "averageRating"));
//            case RATING_ASC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.ASC, "averageRating"));
//            case FEE_DESC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "fee"));
//            case FEE_ASC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.ASC, "fee"));
//            case TOTAL_SERVICE_COUNT_DESC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "totalServiceCount"));
//            case TOTAL_SERVICE_COUNT_ASC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.ASC, "totalServiceCount"));
//            default -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "id"));
//        }

        PageRequest pageRequest = PageRequest.of(page, size);

        List<PetSitter> petSitters = petSitterRepository.findAll(pageRequest).getContent();

        return PetSitterProfileListResponse.of(
                petSitters.stream()
                        .map(petSitter -> PetSitterProfileResponse.of(petSitter, petSitter.getCertifications()))
                        .collect(Collectors.toList())
        );

    }

    /**
     * 펫시터 프로필 목록 조회
     * @param page
     * @param size
     * @param sortType
     * @return
     */
    public PetSitterProfileListResponse findPetSitterProfileListForGeneral(){

        System.out.println("호출");

        PageRequest pageRequest = PageRequest.of(0, 10);

//        switch (sortType){
//            case RATING_DESC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "averageRating"));
//            case RATING_ASC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.ASC, "averageRating"));
//            case FEE_DESC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "fee"));
//            case FEE_ASC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.ASC, "fee"));
//            case TOTAL_SERVICE_COUNT_DESC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "totalServiceCount"));
//            case TOTAL_SERVICE_COUNT_ASC -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.ASC, "totalServiceCount"));
//            default -> pageRequest = PageRequest.of(page, size, Sort.by(Direction.DESC, "id"));
//        }

        List<PetSitter> petSitters = petSitterRepository.findAllApprovedPetSitters(pageRequest).getContent();

        System.out.println(petSitters.size());

        return PetSitterProfileListResponse.of(
                petSitters.stream()
                        .map(petSitter -> PetSitterProfileResponse.of(petSitter, petSitter.getCertifications()))
                        .collect(Collectors.toList())
        );

    }

    @Transactional
    public void approvePetSitter(Long petSitterId){

        PetSitter petSitter = petSitterRepository.findById(petSitterId)
                .orElseThrow(() -> new CustomException(ErrorCode.PET_SITTER_NOT_FOUND));

        petSitter.approve();
    }
}
