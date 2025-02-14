package org.example.petsystem.service.petsitter;

import lombok.RequiredArgsConstructor;
import org.example.petsystem.domain.exception.CustomException;
import org.example.petsystem.domain.exception.ErrorCode;
import org.example.petsystem.domain.member.Member;
import org.example.petsystem.domain.petsitter.PetSitter;
import org.example.petsystem.dto.request.petsitter.PetSitterRegisterRequest;
import org.example.petsystem.repository.member.MemberRepository;
import org.example.petsystem.repository.petsitter.PetSitterRepository;
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
}
