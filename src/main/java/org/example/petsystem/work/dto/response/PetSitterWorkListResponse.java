package org.example.petsystem.work.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetSitterWorkListResponse {

    List<PetSitterWorkResponse> petSitterWorkResponses;
}
