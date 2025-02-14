package org.example.petsystem.domain.petsitter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.domain.member.Member;

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

    private int fee;

    private String introduction;

    private float averageRating;

    private int totalServiceCount;
}
