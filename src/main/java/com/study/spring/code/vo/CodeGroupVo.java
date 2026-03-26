/**
 *	File Name			: CodeGroupVo.java
 *	File Description	: 코드 그룹 정보를 담는 VO 클래스
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-18			yeoun1107			최초작성
 */
package com.study.spring.code.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeGroupVo {
	
	private String groupCd;		// 그룹 코드
	private String groupNm;		// 그룹 명
	private String groupDesc;	// 그룹 설명
	private String useYn;		// 사용 여부
	private String regId;		// 등록자 ID
	private String regDt;		// 등록 일시
	private String updId;		// 수정자 ID
	private String updDt;		// 수정 일시
	
}
