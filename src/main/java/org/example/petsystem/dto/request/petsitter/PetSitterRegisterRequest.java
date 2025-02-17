package org.example.petsystem.dto.request.petsitter;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.member.Member;
import org.example.petsystem.domain.petsitter.PetSitter;
import org.example.petsystem.domain.week.DayOfWeek;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetSitterRegisterRequest {

    private String location;
    private List<DayOfWeek> availableDaysOfWeek;
//    private List<PetCode> petCodes;
    private int fee;
    private String introduction;

    public PetSitter toEntity(Member member){
        return PetSitter.builder()
                .member(member)
                .location(this.location)
                .availableDays(this.availableDaysOfWeek)
//                .petCodes(this.petCodes)
                .fee(this.fee)
                .introduction(this.introduction)
                .averageRating(0)
                .totalServiceCount(0)
                .build();
    }


}
