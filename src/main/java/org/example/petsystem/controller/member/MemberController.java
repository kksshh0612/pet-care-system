package org.example.petsystem.controller.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.dto.request.member.EmailDuplicationCheckRequest;
import org.example.petsystem.dto.request.member.LoginRequest;
import org.example.petsystem.dto.request.member.SignUpRequest;
import org.example.petsystem.service.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/member")
@Tag(name = "회원 API")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "이메일 중복 검사", description = "사용자가 이메일 중복 검사를 한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "이메일을 입력하지 않은 경우")}
            )),
            @ApiResponse(responseCode = "409", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "회원가입 요청한 이메일과 동일한 이메일이 이미 존재하는 경우")}
            ))
    })
    @PostMapping("/email")
    public ResponseEntity<?> checkEmailDuplication(@Valid @RequestBody EmailDuplicationCheckRequest emailDuplicationCheckRequest) {

        memberService.isEmailDuplicated(emailDuplicationCheckRequest.getEmailAddress());

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원가입", description = "사용자가 회원가입 요청을 한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "필수 입력 정보를 입력하지 않은 경우")}
            )),
            @ApiResponse(responseCode = "409", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "회원가입 요청한 이메일과 동일한 이메일이 이미 존재하는 경우")}
            ))
    })
    @PostMapping("")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {

        memberService.signUp(signUpRequest);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "로그인", description = "사용자가 로그인 요청을 한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "필수 입력 정보를 입력하지 않은 경우")}
            )),
            @ApiResponse(responseCode = "409", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "회원가입 요청한 이메일과 동일한 이메일이 이미 존재하는 경우")}
            ))
    })
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest,
                                   HttpServletRequest request) {

        memberService.login(loginRequest);

        // 세션 세팅

        return ResponseEntity.ok().build();
    }
}
