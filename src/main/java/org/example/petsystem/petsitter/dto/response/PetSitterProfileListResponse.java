package org.example.petsystem.petsitter.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSitterProfileListResponse {

    private List<PetSitterProfileResponse> petSitterProfiles;

    public static PetSitterProfileListResponse of(List<PetSitterProfileResponse> petSitterProfiles) {
        return PetSitterProfileListResponse.builder()
                .petSitterProfiles(petSitterProfiles)
                .build();
    }
}
