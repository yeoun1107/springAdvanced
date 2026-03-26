/**
 *	File Name			: CodeDetailVo.java
 *	File Description	: 상세 코드 정보를 담는 VO 클래스
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
public class CodeDetailVo {
	
	private String groupCd;		// 그룹 코드
	private String detailCd;	// 상세 코드
	private String codeNm;		// 코드 명
	private String codeDesc;	// 코드 설명
	private Integer sortOrder;	// 정렬 순서
	private String useYn;		// 사용 여부
	private String regId;		// 등록자 ID
	private String regDt;		// 등록 일시
	private String updId;		// 수정자 ID
	private String updDt;		// 수정 일시
	
}
