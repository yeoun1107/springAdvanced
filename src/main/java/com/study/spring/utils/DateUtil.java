/**
 *	File Name			: DateUtil.java
 *	File Description	: 날짜 및 시간 관련 공통 유틸리티
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-16			yeoun1107				최초생성
 */

package com.study.spring.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	/**
	 * @Description		: 현재 일시를 지정된 포맷으로 반환 (기본: yyyyMMddHHmmss)
	 * @method			: getCurrentDateTime
	 * @return			: String
	 * @Writter			: yeoun1107
	 * @Date			: 2026-03-16
	 */
	public static String getCurrentDateTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	}

	/**
	 * @Description		: 현재 일시를 가독성 좋은 포맷으로 반환
	 * @method			: getCurrentDateTimeReadable
	 * @return			: String
	 * @Writter			: yeoun1107
	 * @Date			: 2026-03-16
	 */
	public static String getCurrentDateTimeReadable() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
