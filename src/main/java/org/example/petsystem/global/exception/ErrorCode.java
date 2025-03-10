package org.example.petsystem.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 400
    EMAIL_PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "이메일/비밀번호가 일치하지 않습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),

    // 401
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),

    // 403
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),

    // 404
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
    PET_SITTER_NOT_FOUND(HttpStatus.NOT_FOUND, "펫시터를 찾을 수 없습니다."),
    CODE_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "코드 그룹을 찾을 수 없습니다."),
    CODE_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "코드를 찾을 수 없습니다."),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "예약 내역을 찾을 수 없습니다."),

    // 409
    EMAIL_ADDRESS_ALREADY_EXIST(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    PET_SITTER_ALREADY_REGISTERED(HttpStatus.CONFLICT, "이미 펫시터 프로필을 등록했습니다");

    private final HttpStatus httpStatus;
    private final String msg;
}
