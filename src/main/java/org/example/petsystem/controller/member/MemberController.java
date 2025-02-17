package org.example.petsystem.controller.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.domain.exception.CustomException;
import org.example.petsystem.domain.exception.ErrorCode;
import org.example.petsystem.dto.request.member.EmailDuplicationCheckRequest;
import org.example.petsystem.dto.request.member.LoginRequest;
import org.example.petsystem.dto.request.member.PasswordChangeRequest;
import org.example.petsystem.dto.request.member.SignUpRequest;
import org.example.petsystem.dto.response.member.LoginSuccessDto;
import org.example.petsystem.dto.response.member.MypageMemberInfoResponse;
import org.example.petsystem.service.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
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
                    examples = {@ExampleObject(name = "필수 입력 정보를 입력하지 않은 경우 OR 아이디/비밀번호가 틀린 경우")}
            ))
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest,
                                   HttpServletRequest request) {

        LoginSuccessDto loginSuccessDto = memberService.login(loginRequest);

        // 세션 세팅
        HttpSession session = request.getSession();
        session.setAttribute("memberId", loginSuccessDto.getMemberId());
        session.setAttribute("memberRole", loginSuccessDto.getMemberRole().toString());

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "로그아웃", description = "사용자가 로그아웃 요청을 한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "")
    })
    @PostMapping("/logout")
    public ResponseEntity<?> findPassword(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.getAttribute("memberId") == null) {
            session.invalidate();
        }

        return ResponseEntity.ok().build();
    }

    // 비밀번호 찾기 구현

    @Operation(summary = "비밀번호 변경", description = "사용자가 비밀번호 변경 요청을 한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "필수 입력 정보를 입력하지 않은 경우 OR 기존 비밀번호가 틀린 경우")}
            )),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "세션ID에 해당하는 회원이 없는 경우")}
            ))
    })
    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordChangeRequest passwordChangeRequest,
                                            HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        if(memberId == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        memberService.changePassword(memberId, passwordChangeRequest);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "마이페이지 회원 정보 조회", description = "사용자가 마이페이지 회원 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = MypageMemberInfoResponse.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = "application/json",
                    examples = {@ExampleObject(name = "세션ID에 해당하는 회원이 없는 경우")}
            ))
    })
    @GetMapping("")
    public ResponseEntity<?> findMemberInfo(HttpSession session) {

//        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("memberId");

        System.out.println("회원 id : " + memberId);

        if(memberId == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        return ResponseEntity.ok(memberService.findMemberInfo(memberId));
    }
}
