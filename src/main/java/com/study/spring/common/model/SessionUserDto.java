/**
 *	File Name			: SessionUserDto.java
 *	File Description	: 세션에 저장할 사용자 정보 DTO
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-16			yeoun1107				최초생성
 */

package com.study.spring.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SessionUserDto {
	private String userId;		// 사용자 ID
	private String userNm;		// 사용자 이름 (추후 DB 확장 시 사용)
	private String loginDt;		// 로그인 일시
}
