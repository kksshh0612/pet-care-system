package org.example.petsystem.payment.service;

import lombok.RequiredArgsConstructor;
import org.example.petsystem.global.exception.CustomException;
import org.example.petsystem.global.exception.ErrorCode;
import org.example.petsystem.payment.domain.Payment;
import org.example.petsystem.payment.dto.request.PaymentSaveRequest;
import org.example.petsystem.payment.repository.PaymentRepository;
import org.example.petsystem.reservation.domain.Reservation;
import org.example.petsystem.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public void save(PaymentSaveRequest paymentSaveRequest) {

        Reservation reservation = reservationRepository.findById(paymentSaveRequest.getId()).
                orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

        Payment payment = new Payment(reservation, paymentSaveRequest.getResponseCode());
        paymentRepository.save(payment);
    }
}
