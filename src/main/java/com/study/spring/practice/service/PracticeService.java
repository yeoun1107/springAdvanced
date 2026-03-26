/**
 *	File Name			: PracticeService.java
 *	File Description	: 연습예제 서비스 인터페이스
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-19			yeoun1107				최초작성
 */
package com.study.spring.practice.service;

import java.util.Map;

public interface PracticeService {

	// KRW 환율 조회 메서드
	Map<String, Object> retrieveKrw() throws Exception;
	
}
