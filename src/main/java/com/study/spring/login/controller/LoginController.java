package com.study.spring.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
 *	File Name			: LoginController.java
 *	File Description	: 로그인 및 회원가입 페이지 이동 컨트롤러
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-12			yeoun1107				최초작성
 */
@Slf4j
@Controller
public class LoginController {


	/**
	 *  @Description  기본 경로 접속 시 로그인 페이지로 이동
	 *  @method 	  retrieveLoginRoot
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-12
	 *  @return 	  String
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-12			    yeoun1107			최초작성
	 */
	@GetMapping("/")
	public String retrieveLoginRoot() {
		log.info("[LoginController] > [retrieveLoginRoot] 시 로그인 페이지로 이동");
		return "redirect:/login";
	}

	
	/**
	 *  @Description  로그인 페이지 이동
	 *  @method 	  retrieveLogin
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-12
	 *  @return 	  String
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-12			    yeoun1107			최초작성
	 */
	@GetMapping("/login")
	public String retrieveLogin() {
		log.info("[LoginController] > [retrieveLogin] 시 로그인 페이지 호출");
		return "login/login";
	}

	
	/**
	 *  @Description  회원가입 페이지 이동
	 *  @method 	  retrieveLogin
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-12
	 *  @return 	  String
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-12			    yeoun1107			최초작성
	 */
	@GetMapping("/signup")
	public String retrieveSignup() {
		log.info("[LoginController] > [retrieveSignup] 시 회원가입 페이지 호출");
		return "login/signup";
	}
}
