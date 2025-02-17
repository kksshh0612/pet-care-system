package org.example.petsystem.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyMemberInfoRequest {

    private String name;
    private String emailAddress;
    private String phoneNumber;

}
