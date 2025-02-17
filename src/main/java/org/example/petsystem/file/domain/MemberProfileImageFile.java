package org.example.petsystem.file.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.petsystem.member.domain.Member;

@Entity
@Getter
@DiscriminatorValue("member")
@NoArgsConstructor
public class MemberProfileImageFile extends File{

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public MemberProfileImageFile(String url, Member member) {
        super(url);
        this.member = member;
    }
}
