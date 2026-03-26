package com.study.spring.common.model;

import com.study.spring.common.constants.ResponseCode;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *	File Name			: CommonResponseDto.java
 *	File Description	: API 공통 응답 DTO
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-12			yeoun1107				최초작성
 */
@Getter
@Setter
@ToString
@Builder
public class CommonResponseDto<T> {

	// 응답 상태 (success, error)
	private String status;

	// 응답 코드 (200, 500 등)
	private int code;

	// 응답 메시지
	private String message;

	// 응답 데이터 (Generic)
	private T data;

	// 성공 응답 전송 메서드
	public static <T> CommonResponseDto<T> success(T data) {
		return CommonResponseDto.<T>builder()
						.status(ResponseCode.SUCCESS.getStatus())
						.code(ResponseCode.SUCCESS.getCode())
						.message(ResponseCode.SUCCESS.getMessage())
						.data(data)
						.build();
	}

	// 성공 메시지 커스텀 응답 전송 메서드 (오버로딩)
	public static <T> CommonResponseDto<T> success(T data, String customMessage) {
		return CommonResponseDto.<T>builder()
						.status(ResponseCode.SUCCESS.getStatus())
						.code(ResponseCode.SUCCESS.getCode())
						.message(customMessage)
						.data(data)
						.build();
	}

	// 실패 응답 전송 메서드
	public static <T> CommonResponseDto<T> fail(ResponseCode responseCode) {
		return CommonResponseDto.<T>builder()
						.status(responseCode.getStatus())
						.code(responseCode.getCode())
						.message(responseCode.getMessage())
						.build();
	}

	// 에러 메시지 커스텀 응답 전송 메서드 (오버로딩)
    public static <T> CommonResponseDto<T> fail(ResponseCode responseCode, String customMessage) {
        return CommonResponseDto.<T>builder()
                .status(responseCode.getStatus())
                .code(responseCode.getCode())
                .message(customMessage) // 전달받은 상세 메시지 사용
                .build();
    }


}
