package org.example.petsystem.petsitter.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.petsitter.domain.DayOfWeek;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetSitterProfileModofyRequest {

    private Long id;
    private String location;
    private List<DayOfWeek> availableDaysOfWeek;
    private int fee;
    private String introduction;
}
