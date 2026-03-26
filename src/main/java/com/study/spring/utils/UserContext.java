/**
 *	File Name			: UserContext.java
 *	File Description	: 현재 쓰레드의 사용자 정보를 관리하는 유틸리티 (ThreadLocal)
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-16			yeoun1107				최초생성
 */

package com.study.spring.utils;

import com.study.spring.common.model.SessionUserDto;

public class UserContext {

	private static final ThreadLocal<SessionUserDto> USER_HOLDER = new ThreadLocal<>();

	/**
	 * @Description		: 현재 쓰레드에 사용자 정보 저장
	 * @method			: setUser
	 * @param			: userDto
	 * @Writter			: yeoun1107
	 * @Date			: 2026-03-16
	 */
	public static void setUser(SessionUserDto userDto) {
		USER_HOLDER.set(userDto);
	}

	/**
	 * @Description		: 현재 쓰레드에서 사용자 정보 조회
	 * @method			: getUser
	 * @return			: SessionUserDto
	 * @Writter			: yeoun1107
	 * @Date			: 2026-03-16
	 */
	public static SessionUserDto getUser() {
		return USER_HOLDER.get();
	}

	/**
	 * @Description		: 현재 쓰레드의 사용자 ID 조회 (Audit용)
	 * 					  사용자ID 값 Null 일 경우 default 지정 : ANONYMOUS
	 * @method			: getUserId
	 * @return			: String
	 * @Writter			: yeoun1107
	 * @Date			: 2026-03-16
	 */
	public static String getUserId() {
		SessionUserDto user = USER_HOLDER.get();
		return user != null ? user.getUserId() : "ANONYMOUS";
	}

	/**
	 * @Description		: 현재 쓰레드의 사용자 정보 삭제 (메모리 누수 방지)
	 * @method			: clear
	 * @Writter			: yeoun1107
	 * @Date			: 2026-03-16
	 */
	public static void clear() {
		USER_HOLDER.remove();
	}
}
