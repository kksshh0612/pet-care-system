package org.example.petsystem.payment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.global.auditing.BaseEntity;
import org.example.petsystem.reservation.domain.Reservation;

@Entity
@Getter
@NoArgsConstructor
public class Payment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

    private String responseCode;

    @Builder
    public Payment(Reservation reservation, String responseCode) {
        this.reservation = reservation;
        this.responseCode = responseCode;
    }
}
