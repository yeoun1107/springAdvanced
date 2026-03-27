package com.study.spring.login.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.spring.common.constants.CommonConstant;
import com.study.spring.common.model.CommonResponseDto;
import com.study.spring.common.model.SessionUserDto;
import com.study.spring.login.model.LoginRequestDto;
import com.study.spring.login.service.LoginService;
import com.study.spring.utils.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.study.spring.login.vo.LoginVo;

/**
 *	File Name			: LoginApiController.java
 *	File Description	: 로그인 및 회원가입 API 컨트롤러
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-12			yeoun1107				최초작성
 * 	2026-03-16			yeoun1107				username -> userId 컬럼 변경 대응
 *  2026-03-25			yeoun1107				전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
 */
@Slf4j
@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginApiController {

	private final LoginService loginService;

	
	/**
	 *  @Description  아이디 중복 여부 확인
	 *  @method 	  existUserId
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-12
	 *  @return 	  CommonResponseDto<Boolean>
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-12			    yeoun1107			최초작성
	 *  2026-03-16				yeoun1107			username -> userId 컬럼 변경 대응
	 *  2026-03-25				yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
	 */
	@GetMapping("/exist-user-id/{userId}")
	public CommonResponseDto<Boolean> existUserId(@PathVariable String userId) throws Exception {

		log.info("[LoginApiController] > [existUserId] 시 아이디 중복 확인 요청 : {}", userId);

		boolean isExist = loginService.existUserId(userId);
		String message = isExist ? "이미 사용중인 아이디입니다." : "사용 가능한 아이디입니다.";

		/*
		CommonResponseDto<Boolean> result = CommonResponseDto.success(isExist, message);
		log.info("아이디 중복 시 ::::::: {}", result.toString());
		return result;
		 */

		return CommonResponseDto.success(isExist, message);
	}


	
	/**
	 *  @Description  로그인 인증 및 세션 생성
	 *  @method 	  retrieveLogin
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-12
	 *  @return 	  CommonResponseDto<SessionUserDto>
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-12			    yeoun1107			최초작성
	 *  2026-03-16				yeoun1107			username -> userId 컬럼 변경 대응
	 *  2026-03-25				yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
	 */
	@PostMapping("/retrieve-login")
	public CommonResponseDto<SessionUserDto> retrieveLogin(HttpSession session, @RequestBody LoginRequestDto loginRequestDto) throws Exception {

		log.info("[LoginApiController] > [retrieveLogin] 시 로그인 요청 : {}", loginRequestDto.getUserId());

			// 1. 회원 정보 조회 및 검증
			LoginVo userVo = loginService.retrieveLogin(loginRequestDto);

			// 2. 세션용 DTO 구성
			SessionUserDto sessionUser = SessionUserDto.builder()
					.userId(userVo.getUserId())
					.userNm(userVo.getUserId()) 
					.loginDt(DateUtil.getCurrentDateTimeReadable())
					.build();

			// 3. 세션 저장
			session.setAttribute(CommonConstant.LOGIN_USER, sessionUser);

			return CommonResponseDto.success(sessionUser, "로그인에 성공했습니다.");
		
	}


	
	/**
	 *  @Description  회원가입 정보 수신 및 비즈니스 로직 수행
	 *  @method 	  createSignup
	 *  @param 		  loginRequestDto
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-12
	 *  @return 	  CommonResponseDto<Object>
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-12			    yeoun1107			최초작성
	 *  2026-03-16				yeoun1107			username -> userId 컬럼 변경 대응
	 */
	@PostMapping("/createSignup")
	public CommonResponseDto<Object> createSignup(@RequestBody LoginRequestDto loginRequestDto) throws Exception {

		log.info("[LoginApiController] > [createSignup] 시 회원가입 요청 수신");
		
		loginService.createSignup(loginRequestDto);

		return CommonResponseDto.success(null, "계정이 성공적으로 생성되었습니다.");
		
	}
}
