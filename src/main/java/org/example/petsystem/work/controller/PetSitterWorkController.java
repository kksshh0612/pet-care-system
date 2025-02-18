package org.example.petsystem.work.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.work.dto.request.PetSitterWorkRegisterRequest;
import org.example.petsystem.work.service.PetSitterWorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pet-sitter-work")
@Tag(name = "펫시터 서비스 API")
public class PetSitterWorkController {

    private final PetSitterWorkService petSitterWorkService;

    @Operation(summary = "펫시터 서비스 등록", description = "펫시터가 펫시터 서비스를 등록한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "입력값이 올바르지 않은 경우")}
            )),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "로그인하지 않은 사용자가 프로필을 등록하는 경우")}
            ))
    })
    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody PetSitterWorkRegisterRequest petSitterWorkRegisterRequest){

        petSitterWorkService.register(petSitterWorkRegisterRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> findPetSitterWorkList(){

        return ResponseEntity.ok().body(petSitterWorkService.findPetSitterWorkList());
    }
}
