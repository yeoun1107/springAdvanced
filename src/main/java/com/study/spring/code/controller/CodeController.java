/**
 *	File Name			: CodeController.java
 *	File Description	: 코드 관리 화면 이동 컨트롤러
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-18			yeoun1107			최초작성
 */
package com.study.spring.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/settings/code")
public class CodeController {

	/**
	 * @Description		: 코드 관리 메인 화면 이동
	 * @method			: retrieveCodeMngeList
	 * @Writter			: yeoun1107
	 * @Date			: 2026-03-18
	 */
	@GetMapping("/codeMngeList")
	public String retrieveCodeMngeList() {
		log.info("[CodeController] > [코드 관리 메인 화면 이동]");
		return "code/codeMngeList";
	}
}
