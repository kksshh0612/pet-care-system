package org.example.petsystem.dto.request.petsitter;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.pet.PetCode;
import org.example.petsystem.domain.week.DayOfWeek;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetSitterProfileModofyRequest {

    private Long id;
    private String location;
    private List<DayOfWeek> availableDaysOfWeek;
    private List<PetCode> petCodes;
    private int fee;
    private String introduction;
}
