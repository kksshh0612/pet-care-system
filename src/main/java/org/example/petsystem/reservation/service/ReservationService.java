package org.example.petsystem.reservation.service;

import lombok.RequiredArgsConstructor;
import org.example.petsystem.global.exception.CustomException;
import org.example.petsystem.global.exception.ErrorCode;
import org.example.petsystem.member.domain.Member;
import org.example.petsystem.member.repository.MemberRepository;
import org.example.petsystem.reservation.domain.Reservation;
import org.example.petsystem.reservation.dto.request.ReservationRegisterRequest;
import org.example.petsystem.reservation.repository.ReservationRepository;
import org.example.petsystem.work.domain.PetSitterWork;
import org.example.petsystem.work.repository.PetSitterWorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final PetSitterWorkRepository petSitterWorkRepository;

    @Transactional
    public Long save(ReservationRegisterRequest registerRequest, Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        PetSitterWork petSitterWork = petSitterWorkRepository.findById(registerRequest.getPetSitterWorkId())
                .orElseThrow(() -> new CustomException(ErrorCode.PET_SITTER_NOT_FOUND));

        Reservation saveReservation = reservationRepository.save(new Reservation(member, petSitterWork));

        return saveReservation.getId();

    }
}
