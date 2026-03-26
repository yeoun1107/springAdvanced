/**
 *	File Name			: PracticeController.java
 *	File Description	: 연습예제 화면 이동 컨트롤러
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-19			yeoun1107			최초작성
 */
package com.study.spring.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/practice")
public class PracticeController {

    /**
	 * @Description		: RestClient 예제 화면 이동
	 * @method			: retrieveRestClientEx
	 * @Writter			: yeoun1107
	 * @Date			: 2026-03-18
	 */
	@GetMapping("/restClientEx")
	public String retrieveRestClientEx() {
		log.info("[PracticeController] > [RestClient 예제 화면 이동]");
		return "practice/restClientEx";
	}
    
}
