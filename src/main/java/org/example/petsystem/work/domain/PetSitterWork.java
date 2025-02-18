package org.example.petsystem.work.domain;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.petsitter.domain.PetSitter;

@Entity
@Getter
@NoArgsConstructor
public class PetSitterWork {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_sitter_work_id")
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<ServiceType> serviceTypes = new ArrayList<>();     // 방문돌봄 위탁돌봄 산책

    @ElementCollection(fetch = FetchType.EAGER)
    private List<PetSize> availableSizes = new ArrayList<>();   // 소형견(10kg이하) 중형견(10kg~25kg) 대형견(25kg초과)

    private String availableDay;    // 가능 날짜

    private int fee;                // 요금

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_sitter_id")
    PetSitter petSitter;

    @Builder
    public PetSitterWork(List<ServiceType> serviceTypes, List<PetSize> availableSizes, String availableDay, int fee,
                         PetSitter petSitter) {
        this.serviceTypes = serviceTypes;
        this.availableSizes = availableSizes;
        this.availableDay = availableDay;
        this.fee = fee;
        this.petSitter = petSitter;
    }
}
