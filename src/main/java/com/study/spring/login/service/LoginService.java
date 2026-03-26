package com.study.spring.login.service;

import com.study.spring.login.model.LoginRequestDto;

import com.study.spring.login.vo.LoginVo;

/**
 *	File Name			: LoginService.java
 *	File Description	: 로그인 및 회원가입 서비스 인터페이스
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-12			yeoun1107				최초작성
 */
public interface LoginService {

	// 회원가입 정보 저장
	void createSignup(LoginRequestDto loginRequestDto) throws Exception;

	// 아이디 중복 여부 확인
	boolean existUserId(String userId) throws Exception;

	// 로그인 사용자 정보 조회 및 검증
	LoginVo retrieveLogin(LoginRequestDto loginRequestDto) throws Exception;
}
