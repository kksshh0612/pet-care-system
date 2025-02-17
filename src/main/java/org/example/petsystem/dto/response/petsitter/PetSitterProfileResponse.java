package org.example.petsystem.dto.response.petsitter;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.certification.Certification;
import org.example.petsystem.domain.petsitter.PetSitter;
import org.example.petsystem.domain.week.DayOfWeek;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSitterProfileResponse {

    private Long id;
    private String location;
    private List<String> availableDays;
//    private List<String> petTypes;
    private int fee;
    private String introduction;
    private float averageRating;
    private int totalServiceCount;
    private List<String> certificationNames;

    public static PetSitterProfileResponse of(PetSitter petSitter, List<Certification> certifications) {
        return PetSitterProfileResponse.builder()
                .id(petSitter.getId())
                .location(petSitter.getLocation())
                .availableDays(petSitter.getAvailableDays().stream().map(DayOfWeek::getName).toList())
//                .petTypes(petSitter.getPetCodes().stream().map(PetCode::getName).toList())
                .fee(petSitter.getFee())
                .introduction(petSitter.getIntroduction())
                .averageRating(petSitter.getAverageRating())
                .totalServiceCount(petSitter.getTotalServiceCount())
                .certificationNames(certifications.stream().map(Certification::getName).toList())
                .build();
    }
}
