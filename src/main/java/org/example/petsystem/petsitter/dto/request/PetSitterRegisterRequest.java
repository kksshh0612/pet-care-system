package org.example.petsystem.petsitter.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.member.domain.Member;
import org.example.petsystem.pet.domain.PetCode;
import org.example.petsystem.petsitter.domain.PetSitter;
import org.example.petsystem.petsitter.domain.DayOfWeek;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetSitterRegisterRequest {

    private String location;
    private List<DayOfWeek> availableDaysOfWeek;
    private List<PetCode> petCodes;
    private int fee;
    private String introduction;

    public PetSitter toEntity(Member member){
        return PetSitter.builder()
                .member(member)
                .location(this.location)
                .availableDays(this.availableDaysOfWeek)
                .petCodes(this.petCodes)
                .fee(this.fee)
                .introduction(this.introduction)
                .averageRating(0)
                .totalServiceCount(0)
                .isApproved(false)
                .build();
    }


}
