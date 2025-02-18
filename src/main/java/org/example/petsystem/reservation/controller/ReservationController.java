package org.example.petsystem.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.example.petsystem.reservation.dto.request.ReservationRegisterRequest;
import org.example.petsystem.reservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("")
    public ResponseEntity<?> saveReservation(@RequestBody ReservationRegisterRequest reservationRegisterRequest,
                                             @SessionAttribute("memberId") Long memberId){

        Long savedId = reservationService.save(reservationRegisterRequest, memberId);
        return ResponseEntity.ok(savedId);
    }
}
