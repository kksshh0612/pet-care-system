package org.example.petsystem.reservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.reservation.domain.Reservation;
import org.example.petsystem.work.domain.PetSitterWork;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRegisterRequest {

    private Long petSitterWorkId;
}
