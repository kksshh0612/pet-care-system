package org.example.petsystem.reservation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "예약 API")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "예약 정보 저장", description = "예약 정보를 저장한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "권한이 없는 사용자가 접근할 경우")}
            )),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "펫시터 서비스 정보를 찾을 수 없는 경우")}
            ))
    })
    @PostMapping("")
    public ResponseEntity<?> saveReservation(@RequestBody ReservationRegisterRequest reservationRegisterRequest,
                                             @SessionAttribute("memberId") Long memberId){

        Long savedId = reservationService.save(reservationRegisterRequest, memberId);
        return ResponseEntity.ok(savedId);
    }
}
