package com.study.spring.login.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.study.spring.login.dao.LoginDao;
import com.study.spring.login.model.LoginRequestDto;
import com.study.spring.login.service.impl.LoginServiceImpl;
import com.study.spring.login.vo.LoginVo;

/**
 *	File Name			: LoginServiceTest.java
 *	File Description	: 회원가입 비즈니스 로직 테스트 (TDD 준수)
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-13			yeoun1107				최초작성
 */
@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

	@Mock
	private LoginDao loginDao;

	@InjectMocks
	private LoginServiceImpl loginService;

	@Test
	@DisplayName("회원가입 시 날짜가 yyyyMMddHHmmss 형식으로 생성되는지 검증")
	void createSignup_DateFormatTest() throws Exception {
		// Given
		LoginRequestDto requestDto = new LoginRequestDto();
		requestDto.setUserId("testuser");
		requestDto.setPassword("testpw1234");

		// When
		loginService.createSignup(requestDto);

		// Then
		ArgumentCaptor<LoginVo> loginVoCaptor = ArgumentCaptor.forClass(LoginVo.class);
		verify(loginDao).insertUser(loginVoCaptor.capture());

		LoginVo capturedVo = loginVoCaptor.getValue();
		
		assertNotNull(capturedVo.getCreateDt());
		assertEquals(14, capturedVo.getCreateDt().length(), "날짜 길이는 14자여야 함 (yyyyMMddHHmmss)");
		assertTrue(capturedVo.getCreateDt().matches("\\d{14}"), "날짜는 숫자로만 구성되어야 함");
		
		System.out.println("생성된 날짜 : " + capturedVo.getCreateDt());
	}
}
