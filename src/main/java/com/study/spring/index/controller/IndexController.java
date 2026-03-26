/**
 *	File Name			: IndexController.java
 *	File Description	: 메인 화면 처리 컨트롤러
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-16			yeoun1107				최초생성
 */

package com.study.spring.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	/**
	 * @Description		: 메인 화면(Dashboard) 호출
	 * @method			: retrieveIndex
	 * @return			: String (index/index.html)
	 * @Writter			: yeoun1107
	 * @Date			: 2026-03-16
	 * @EditHistory		: 
	 */
	@GetMapping("/index")
	public String retrieveIndex() {
		log.info("[IndexController] > [retrieveIndex] 시 메인 대시보드 호출");
		return "index/index";
	}
}
