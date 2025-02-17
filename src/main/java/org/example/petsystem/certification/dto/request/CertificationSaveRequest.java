package org.example.petsystem.certification.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CertificationSaveRequest {

    private String name;
    private Long petSitterId;
}
