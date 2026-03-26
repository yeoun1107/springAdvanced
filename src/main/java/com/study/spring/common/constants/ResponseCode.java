package com.study.spring.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *	File Name			: ResponseCode.java
 *	File Description	: 응답 상태코드 및 메시지 관리 Enum
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-19			yeoun1107				최초작성
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("SUCCESS", 200, "성공적으로 처리되었습니다."),
    NOT_FOUND("FAIL", 404, "요청한 데이터를 찾을 수 없습니다."),
    SERVER_ERROR("ERROR", 500, "서버 내부 오류가 발생하였습니다. 관리자에게 문의하세요."),

    BUSINESS_ERROR("FAIL", 400, "[비즈니스 로직 예외] 로 인하여 요청을 처리할 수 없습니다.");

    private final String status;
    private final int code;
    private final String message;
}
