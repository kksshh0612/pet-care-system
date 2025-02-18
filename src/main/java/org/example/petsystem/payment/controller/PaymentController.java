package org.example.petsystem.payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.payment.dto.request.PaymentSaveRequest;
import org.example.petsystem.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
@Tag(name = "결제 API")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "결제 정보 저장", description = "사용자가 결제 정보를 저장한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "필수 입력 정보를 입력하지 않은 경우 OR 아이디/비밀번호가 틀린 경우")}
            )),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "예약 번호에 해당하는 예약 정보가 없는 경우")}
            ))
    })
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody PaymentSaveRequest paymentSaveRequest){

        paymentService.save(paymentSaveRequest);
        return ResponseEntity.ok().build();
    }
}
