/**
 *	File Name			: AuthInterceptor.java
 *	File Description	: 인증 및 사용자 컨텍스트 관리 인터셉터
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-16			yeoun1107				최초생성
 * 	2026-03-16			yeoun1107				공통 상수 및 postHandle(모델 주입) 추가
 */

package com.study.spring.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.study.spring.common.constants.CommonConstant;
import com.study.spring.common.model.SessionUserDto;
import com.study.spring.utils.UserContext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

	/**
	 *  @Description  컨트롤러 실행 전 세션 정보를 UserContext에 셋팅
	 *  @method 	  preHandle
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-17
	 *  @return 	  boolean
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-17			    yeoun1107			최초작성
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		SessionUserDto loginUser = (SessionUserDto) session.getAttribute(CommonConstant.LOGIN_USER);

		if (loginUser != null) {
			// 현재 쓰레드에 사용자 정보 공유
			UserContext.setUser(loginUser);
			log.debug("[AuthInterceptor] UserContext 셋팅 완료: {}", loginUser.getUserId());
		}

		return true;
	}


	/**
	 *  @Description  컨트롤러 실행 후, 뷰 렌더링 전 유저 정보를 모델에 주입
	 *  @method 	  postHandle
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-17
	 *  @return 	  void
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-17			    yeoun1107			최초작성
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			SessionUserDto loginUser = UserContext.getUser();
			if (loginUser != null) {
				// 모든 페이지에서 ${loginUser}로 접근 가능하게 ModelAndView에 login 객체 전달
				modelAndView.addObject("loginUser", loginUser);
			}
		}
	}

	
	/**
	 *  @Description  요청 종료 후 UserContext 정리 (메모리 누수 방지 필수)
	 *  @method 	  afterCompletion
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-17
	 *  @return 	  void
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-17			    yeoun1107			최초작성
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		UserContext.clear();
		log.debug("[AuthInterceptor] UserContext 정리 완료");
	}
}
