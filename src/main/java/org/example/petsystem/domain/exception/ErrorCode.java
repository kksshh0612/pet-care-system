package org.example.petsystem.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 401
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),

    // 404
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
    PET_SITTER_NOT_FOUND(HttpStatus.NOT_FOUND, "펫시터 정보를 찾을 수 없습니다."),

    // 409
    EMAIL_ADDRESS_ALREADY_EXIST(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    PET_SITTER_ALREADY_REGISTERED(HttpStatus.CONFLICT, "이미 등록된 펫시터입니다.");

    private final HttpStatus httpStatus;
    private final String msg;
}
