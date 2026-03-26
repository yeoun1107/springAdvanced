package com.study.spring.login.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *	File Name			: LoginVo.java
 *	File Description	: tb_user 테이블과 1대1 매핑되는 VO 객체
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-13			yeoun1107				최초작성
 */
@Getter
@Setter
@ToString
public class LoginVo {

	/** 사용자 고유 번호 */
	private Integer userNo;

	/** 사용자 아이디 */
	private String userId;

	/** 사용자 비밀번호 */
	private String password;

	/** 삭제 여부 (N: 미삭제, Y: 삭제) */
	private String delYn;

	/** 생성 일시 (yyyyMMddHHmmss) */
	private String createDt;

	/** 수정 일시 (yyyyMMddHHmmss) */
	private String updateDt;
}
