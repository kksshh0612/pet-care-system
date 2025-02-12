package org.example.petsystem.service.member;

import lombok.RequiredArgsConstructor;
import org.example.petsystem.domain.member.Member;
import org.example.petsystem.dto.request.SignUpRequest;
import org.example.petsystem.repository.member.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 이메일 존재 여부 확인 코드 삽입

    /**
     * 회원가입
     * @param signUpRequest
     */
    @Transactional
    public void signUp(SignUpRequest signUpRequest) {

        if(memberRepository.findByEmailAddress(signUpRequest.getEmailAddress()).isPresent()) {
            // 예외 throw
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Member member = signUpRequest.toEntity(encodedPassword);
        memberRepository.save(member);
    }
}
