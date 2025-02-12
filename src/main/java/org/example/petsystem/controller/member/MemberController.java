package org.example.petsystem.controller.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.dto.request.SignUpRequest;
import org.example.petsystem.service.member.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {

        memberService.signUp(signUpRequest);

        return ResponseEntity.ok().build();
    }
}
