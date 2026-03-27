package com.study.spring.utils;

import com.study.spring.common.constants.ResponseCode;

/**
 *	File Name			: AssertUtil.java
 *	File Description	: Spring Assert 유틸리티
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-27			yeoun1107				최초작성
 */
public class AssertUtil {

    /**
     * @Description String 빈값 + null 체크
     * @method 		isNull
     * @Writter 	yeoun1107
     * @Date 		2026-03-27
	 * @param 		str, msg
     * 
     * @EditHistory
     * Date					Author				Description
     * --------------------------------------------------------------
     * 2026-03-27			yeoun1107			최초작성
     */
    public static void isNull(String str, String msg) {
        if(str == null || str.trim().isEmpty()) {
            throw new CustomException(ResponseCode.BUSINESS_ERROR, msg);
        }
    }
    
}
