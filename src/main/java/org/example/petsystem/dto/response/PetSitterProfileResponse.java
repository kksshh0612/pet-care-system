package org.example.petsystem.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.file.CertificationFile;
import org.example.petsystem.domain.member.Member;
import org.example.petsystem.domain.pet.PetCode;
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
    private List<String> petTypes;
    private int fee;
    private String introduction;
    private float averageRating;
    private int totalServiceCount;
    private List<String> certificationUrls;

    public static PetSitterProfileResponse of(PetSitter petSitter, List<CertificationFile> files) {

        return PetSitterProfileResponse.builder()
                .id(petSitter.getId())
                .location(petSitter.getLocation())
                .availableDays(petSitter.getAvailableDays().stream().map(DayOfWeek::getName).toList())
                .petTypes(petSitter.getPetCodes().stream().map(PetCode::getName).toList())
                .fee(petSitter.getFee())
                .introduction(petSitter.getIntroduction())
                .averageRating(petSitter.getAverageRating())
                .totalServiceCount(petSitter.getTotalServiceCount())
                .certificationUrls(files.stream().map(CertificationFile::getUrl).toList())
                .build();
    }
}
