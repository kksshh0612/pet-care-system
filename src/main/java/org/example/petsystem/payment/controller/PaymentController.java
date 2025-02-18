package org.example.petsystem.payment.controller;

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
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody PaymentSaveRequest paymentSaveRequest){

        paymentService.save(paymentSaveRequest);
        return ResponseEntity.ok().build();
    }
}
