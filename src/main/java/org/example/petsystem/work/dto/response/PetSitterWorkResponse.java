package org.example.petsystem.work.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.member.domain.Member;
import org.example.petsystem.petsitter.domain.PetSitter;
import org.example.petsystem.work.domain.PetSitterWork;
import org.example.petsystem.work.domain.PetSize;
import org.example.petsystem.work.domain.ServiceType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSitterWorkResponse {

    private Long id;
    private String petSitterName;
    private String introduction;
    private List<ServiceType> serviceTypes;     // 방문돌봄 위탁돌봄 산책
    private List<PetSize> availableSizes;       // 소형견(10kg이하) 중형견(10kg~25kg) 대형견(25kg초과)
    private String availableDay;                // 가능 날짜
    private int fee;                            // 요금

    public static PetSitterWorkResponse of(PetSitter petSitter, PetSitterWork petSitterWork){
        return PetSitterWorkResponse.builder()
                .id(petSitterWork.getId())
                .petSitterName(petSitter.getMember().getName())
                .introduction(petSitter.getIntroduction())
                .serviceTypes(petSitterWork.getServiceTypes())
                .availableSizes(petSitterWork.getAvailableSizes())
                .availableDay(petSitterWork.getAvailableDay())
                .fee(petSitterWork.getFee())
                .build();
    }
}
