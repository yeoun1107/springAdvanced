/**
 *	File Name			: WebConfig.java
 *	File Description	: 웹 설정 및 인터셉터 등록
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-16			yeoun1107				최초생성
 */

package com.study.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.study.spring.common.interceptor.AuthInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final AuthInterceptor authInterceptor;

	/**
	 * @Description		: 인터셉터 등록 및 경로 설정
	 * @method			: addInterceptors
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
				.addPathPatterns("/**") // 모든 경로 가로채기
				.excludePathPatterns(
					"/css/**", 
					"/js/**", 
					"/images/**", 
					"/error", 
					"/favicon.ico"
				); // 정적 리소스 및 기본 예외 경로 제외
	}
}
