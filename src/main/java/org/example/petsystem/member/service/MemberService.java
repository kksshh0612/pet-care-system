package org.example.petsystem.member.service;

import lombok.RequiredArgsConstructor;
import org.example.petsystem.global.exception.CustomException;
import org.example.petsystem.global.exception.ErrorCode;
import org.example.petsystem.member.domain.Member;
import org.example.petsystem.member.dto.request.LoginRequest;
import org.example.petsystem.member.dto.request.PasswordChangeRequest;
import org.example.petsystem.member.dto.request.SignUpRequest;
import org.example.petsystem.member.dto.response.LoginSuccessDto;
import org.example.petsystem.member.dto.response.MypageMemberInfoResponse;
import org.example.petsystem.member.repository.MemberRepository;
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
    public LoginSuccessDto login(LoginRequest loginRequest){

        Member member = memberRepository.findByEmailAddress(loginRequest.getEmailAddress())
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_PASSWORD_NOT_MATCH));

        if(!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.EMAIL_PASSWORD_NOT_MATCH);
        }

        return LoginSuccessDto.of(member);
    }

    /**
     * 비밀번호 변경
     * @param memberId
     * @param passwordChangeRequest
     */
    @Transactional
    public void changePassword(Long memberId, PasswordChangeRequest passwordChangeRequest){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(passwordChangeRequest.getOldPassword(), member.getPassword())){
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        String newPassword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
        member.changePassword(newPassword);
    }

    /**
     * 마이페이지 회원 정보 단건 조회
     * @param memberId
     * @return
     */
    public MypageMemberInfoResponse findMemberInfo(Long memberId){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return MypageMemberInfoResponse.from(member);
    }

}
