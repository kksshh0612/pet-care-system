package org.example.petsystem.petsitter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.global.exception.CustomException;
import org.example.petsystem.global.exception.ErrorCode;
import org.example.petsystem.petsitter.domain.SortType;
import org.example.petsystem.petsitter.dto.request.PetSitterProfileModifyRequest;
import org.example.petsystem.petsitter.dto.request.PetSitterRegisterRequest;
import org.example.petsystem.petsitter.dto.response.PetSitterProfileListResponse;
import org.example.petsystem.petsitter.dto.response.PetSitterProfileResponse;
import org.example.petsystem.petsitter.service.PetSitterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pet-sitter")
@Tag(name = "펫시터 API")
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
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PetSitterProfileResponse.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "로그인하지 않은 사용자가 프로필을 등록하는 경우")}
            )),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "memberId에 해당하는 회원을 찾을 수 없는 경우")}
            ))
    })
    @GetMapping("/{pet-sitter-id}")
    public ResponseEntity<?> findPetSitterProfile(@PathVariable("pet-sitter-id") Long petSitterId){

        return ResponseEntity.ok(petSitterService.findPetSitterProfile(petSitterId));
    }

    @Operation(summary = "나의 펫시터 프로필 조회", description = "사용자가 펫시터 프로필을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PetSitterProfileResponse.class))),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "로그인하지 않은 사용자가 프로필을 등록하는 경우")}
            )),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "memberId에 해당하는 회원을 찾을 수 없는 경우")}
            ))
    })
    @GetMapping("/my-pet-sitter")
    public ResponseEntity<?> findMyPetSitterProfile(@SessionAttribute("memberId") Long memberId){

        return ResponseEntity.ok(petSitterService.findMyPetSitterProfile(memberId));
    }

    @Operation(summary = "펫시터 프로필 수정", description = "사용자가 펫시터 프로필을 수정한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "로그인하지 않은 사용자가 프로필을 등록하는 경우")}
            )),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "memberId에 해당하는 회원을 찾을 수 없는 경우")}
            ))
    })
    @PatchMapping("")
    public ResponseEntity<?> modifyPetSitterProfile(@Valid @RequestBody PetSitterProfileModifyRequest modifyRequest,
                                                    @SessionAttribute("memberId") Long memberId){

        if(memberId == null){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        petSitterService.modifyPetSitterProfile(memberId, modifyRequest);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "펫시터 프로필 목록 조회", description = "사용자가 펫시터 프로필 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PetSitterProfileListResponse.class)))

    })
    @GetMapping("/list")
    public ResponseEntity<?> findPetSitterProfileListForGeneral(){

        return ResponseEntity.ok(petSitterService.findPetSitterProfileListForGeneral());
    }

    @Operation(summary = "관리자의 펫시터 프로필 목록 조회", description = "관리자가 펫시터 프로필 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PetSitterProfileListResponse.class)))

    })
    @GetMapping("/waiting-approval-list")
    public ResponseEntity<?> findPetSitterProfileListForManager(@RequestParam("page") int page, @RequestParam("size") int size){

        return ResponseEntity.ok(petSitterService.findPetSitterProfileListForManager(page, size));
    }

    @Operation(summary = "펫시터 프로필 승인", description = "관리자가 펫시터 프로필 등록을 승인한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "권한이 없는 사용자가 접근할 경우")}
            ))
    })
    @PatchMapping("/{pet-sitter-id}/approve")
    public ResponseEntity<?> approvePetSitter(@PathVariable("pet-sitter-id") Long petSitterId){

        petSitterService.approvePetSitter(petSitterId);
        return ResponseEntity.ok().build();
    }
}
