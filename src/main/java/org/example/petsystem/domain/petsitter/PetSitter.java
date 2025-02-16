package org.example.petsystem.domain.petsitter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.certification.Certification;
import org.example.petsystem.domain.member.Member;
import org.example.petsystem.domain.pet.PetCode;
import org.example.petsystem.domain.week.DayOfWeek;

@Entity
@Getter
@NoArgsConstructor
public class PetSitter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_sitter_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    private String location;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<DayOfWeek> availableDays;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<PetCode> petCodes = new ArrayList<>();

    private int fee;

    private String introduction;

    private float averageRating;

    private int totalServiceCount;

    @OneToMany(mappedBy = "petSitter", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Certification> certifications = new ArrayList<>();

    @Builder
    public PetSitter(Member member, String location, List<DayOfWeek> availableDays, List<PetCode> petCodes, int fee,
                     String introduction, float averageRating, int totalServiceCount,
                     List<Certification> certifications) {
        this.member = member;
        this.location = location;
        this.availableDays = availableDays;
        this.petCodes = petCodes;
        this.fee = fee;
        this.introduction = introduction;
        this.averageRating = averageRating;
        this.totalServiceCount = totalServiceCount;
        this.certifications = certifications;
    }

    //== 연관관계 편의 매서드 ==//
    public void addCertification(Certification certification) {
        this.certifications.add(certification);
    }

    //== 비지니스 로직 ==//
    public void modify(String location, List<DayOfWeek> availableDays, List<PetCode> petCodes, int fee, String introduction){
        this.location = location;
        this.availableDays = availableDays;
        this.petCodes = petCodes;
        this.fee = fee;
        this.introduction = introduction;
    }
}
