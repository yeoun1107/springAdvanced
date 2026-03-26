/**
 *	File Name			: CodeService.java
 *	File Description	: 코드 관리 비즈니스 로직 인터페이스
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-18			yeoun1107			최초작성
 */
package com.study.spring.code.service;

import java.util.List;
import com.study.spring.code.vo.CodeGroupVo;
import com.study.spring.code.vo.CodeDetailVo;

public interface CodeService {

	/* 그룹 코드 조회 */
	List<CodeGroupVo> retrieveCodeGroupList(CodeGroupVo codeGroupVo) throws Exception;
	CodeGroupVo retrieveCodeGroup(String groupCd) throws Exception;
	
	/* 그룹 코드 관리 */
	int createCodeGroup(CodeGroupVo codeGroupVo) throws Exception;
	int updateCodeGroup(CodeGroupVo codeGroupVo) throws Exception;
	int deleteCodeGroup(CodeGroupVo codeGroupVo) throws Exception;
	
	/* 상세 코드 조회 */
	List<CodeDetailVo> retrieveCodeDetailList(CodeDetailVo codeDetailVo) throws Exception;
	CodeDetailVo retrieveCodeDetail(CodeDetailVo codeDetailVo) throws Exception;
	
	/* 상세 코드 관리 */
	int createCodeDetail(CodeDetailVo codeDetailVo) throws Exception;
	int updateCodeDetail(CodeDetailVo codeDetailVo) throws Exception;
	int deleteCodeDetail(CodeDetailVo codeDetailVo) throws Exception;
}
