package org.example.petsystem.work.dto.request;

import jakarta.persistence.ElementCollection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.petsitter.domain.PetSitter;
import org.example.petsystem.work.domain.PetSitterWork;
import org.example.petsystem.work.domain.PetSize;
import org.example.petsystem.work.domain.ServiceType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetSitterWorkRegisterRequest {

    private Long petSitterId;
    private List<ServiceType> serviceTypes;     // 방문돌봄 위탁돌봄 산책
    private List<PetSize> availableSizes;       // 소형견(10kg이하) 중형견(10kg~25kg) 대형견(25kg초과)
    private String availableDay;                // 가능 날짜
    private int fee;                            // 요금

    public PetSitterWork toEntity(PetSitter petSitter){
        return PetSitterWork.builder()
                .serviceTypes(serviceTypes)
                .availableSizes(availableSizes)
                .availableDay(availableDay)
                .fee(fee)
                .petSitter(petSitter)
                .build();
    }
}
