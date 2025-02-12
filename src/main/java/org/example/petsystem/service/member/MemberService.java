package org.example.petsystem.service.member;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.petsystem.domain.exception.CustomException;
import org.example.petsystem.domain.exception.ErrorCode;
import org.example.petsystem.domain.member.Member;
import org.example.petsystem.dto.request.member.EmailDuplicationCheckRequest;
import org.example.petsystem.dto.request.member.LoginRequest;
import org.example.petsystem.dto.request.member.PasswordCheckRequest;
import org.example.petsystem.dto.request.member.SignUpRequest;
import org.example.petsystem.dto.response.member.MypageMemberInfoResponse;
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

    /**
     * 이메일 중복 검사
     * @param emailAddress
     */
    public void isEmailDuplicated(String emailAddress) {

        if (memberRepository.findByEmailAddress(emailAddress).isPresent()) {
            throw new CustomException(ErrorCode.EMAIL_ADDRESS_ALREADY_EXIST);
        }
    }

    /**
     * 회원가입
     * @param signUpRequest
     */
    @Transactional
    public void signUp(SignUpRequest signUpRequest) {

        isEmailDuplicated(signUpRequest.getEmailAddress());

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        Member member = signUpRequest.toEntity(encodedPassword);
        memberRepository.save(member);
    }

    /**
     * 로그인
     * @param loginRequest
     */
    public void login(LoginRequest loginRequest){

        Member member = memberRepository.findByEmailAddress(loginRequest.getEmailAddress())
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_PASSWORD_NOT_MATCH));

        if(!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.EMAIL_PASSWORD_NOT_MATCH);
        }
    }



    @Transactional
    public void checkPassword(PasswordCheckRequest passwordCheckRequest) {


    }
}
