package org.example.petsystem.controller.petsitter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.example.petsystem.domain.exception.CustomException;
import org.example.petsystem.domain.exception.ErrorCode;
import org.example.petsystem.dto.request.petsitter.PetSitterRegisterRequest;
import org.example.petsystem.service.petsitter.PetSitterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pet-sitter")
public class PetSitterController {

    private final PetSitterService petSitterService;

    @Operation(summary = "펫시터 프로필 등록", description = "사용자가 펫시터 프로필을 등록한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "입력값이 올바르지 않은 경우")}
            )),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "로그인하지 않은 사용자가 프로필을 등록하는 경우")}
            )),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "memberId에 해당하는 회원을 찾을 수 없는 경우")}
            )),
            @ApiResponse(responseCode = "409", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "이미 펫시터 프로필을 등록한 사용자가 프로필을 등록하는 경우")}
            ))
    })
    @PostMapping("")
    public ResponseEntity<?> register(@Valid @RequestBody PetSitterRegisterRequest petSitterRegisterRequest,
                                      HttpSession session){

        Long memberId = (Long) session.getAttribute("memberId");

        if(memberId == null){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        petSitterService.register(memberId, petSitterRegisterRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "펫시터 프로필 조회", description = "사용자가 펫시터 프로필을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "로그인하지 않은 사용자가 프로필을 등록하는 경우")}
            )),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "memberId에 해당하는 회원을 찾을 수 없는 경우")}
            ))
    })
    @GetMapping("")
    public ResponseEntity<?> findPetSitterInfo(HttpSession session){

        Long memberId = (Long) session.getAttribute("memberId");

        if(memberId == null){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        return ResponseEntity.ok(petSitterService.findPetSitterProfile(memberId));
    }
}
