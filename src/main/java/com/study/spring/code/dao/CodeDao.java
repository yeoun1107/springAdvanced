/**
 *	File Name			: CodeDao.java
 *	File Description	: 코드 관리 관련 DB 접근 인터페이스
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-18			yeoun1107			최초작성
 */
package com.study.spring.code.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.study.spring.code.vo.CodeGroupVo;
import com.study.spring.code.vo.CodeDetailVo;

@Mapper
public interface CodeDao {

	/* 그룹 코드 조회 */
	List<CodeGroupVo> selectCodeGroupList(CodeGroupVo codeGroupVo) throws Exception;
	CodeGroupVo selectCodeGroup(String groupCd) throws Exception;
	
	/* 그룹 코드 관리 */
	int insertCodeGroup(CodeGroupVo codeGroupVo) throws Exception;
	int updateCodeGroup(CodeGroupVo codeGroupVo) throws Exception;
	int deleteCodeGroup(CodeGroupVo codeGroupVo) throws Exception;
	
	/* 상세 코드 조회 */
	List<CodeDetailVo> selectCodeDetailList(CodeDetailVo codeDetailVo) throws Exception;
	CodeDetailVo selectCodeDetail(CodeDetailVo codeDetailVo) throws Exception;
	
	/* 상세 코드 관리 */
	int insertCodeDetail(CodeDetailVo codeDetailVo) throws Exception;
	int updateCodeDetail(CodeDetailVo codeDetailVo) throws Exception;
	int deleteCodeDetail(CodeDetailVo codeDetailVo) throws Exception;
}
