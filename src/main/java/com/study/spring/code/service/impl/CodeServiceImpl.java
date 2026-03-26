package com.study.spring.code.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.study.spring.code.dao.CodeDao;
import com.study.spring.code.service.CodeService;
import com.study.spring.code.vo.CodeGroupVo;
import com.study.spring.code.vo.CodeDetailVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *	File Name			: CodeServiceImpl.java
 *	File Description	: 코드 관리 비즈니스 로직 구현 클래스
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-18			yeoun1107			최초작성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

	private final CodeDao codeDao;

	/* 그룹 코드 조회 */
	/**
	 *  @Description  코드 그룹 목록 조회
	 *  @method 	  retrieveCodeGroupList
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  List<CodeGroupVo>
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 */
	@Override
	public List<CodeGroupVo> retrieveCodeGroupList(CodeGroupVo codeGroupVo) throws Exception {
		return codeDao.selectCodeGroupList(codeGroupVo);
	}


	/**
	 *  @Description  코드 그룹 상세 조회
	 *  @method 	  retrieveCodeGroup
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  CodeGroupVo
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 */
	@Override
	public CodeGroupVo retrieveCodeGroup(String groupCd) throws Exception {
		return codeDao.selectCodeGroup(groupCd);
	}



	/* 그룹 코드 관리 */
	/**
	 *  @Description  코드 그룹 등록
	 *  @method 	  createCodeGroup
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  int
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createCodeGroup(CodeGroupVo codeGroupVo) throws Exception {
		return codeDao.insertCodeGroup(codeGroupVo);
	}


	/**
	 *  @Description  코드 그룹 수정
	 *  @method 	  updateCodeGroup
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  int
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateCodeGroup(CodeGroupVo codeGroupVo) throws Exception {
		return codeDao.updateCodeGroup(codeGroupVo);
	}


	/**
	 *  @Description  코드 그룹 삭제
	 *  @method 	  deleteCodeGroup
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  int
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteCodeGroup(CodeGroupVo codeGroupVo) throws Exception {
		return codeDao.deleteCodeGroup(codeGroupVo);
	}



	/* 상세 코드 조회 */
	/**
	 *  @Description  코드 상세 목록 조회
	 *  @method 	  retrieveCodeDetailList
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  List<CodeDetailVo>
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 */
	@Override
	public List<CodeDetailVo> retrieveCodeDetailList(CodeDetailVo codeDetailVo) throws Exception {
		return codeDao.selectCodeDetailList(codeDetailVo);
	}


	/**
	 *  @Description  코드 상세 상세 조회
	 *  @method 	  retrieveCodeDetail
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  CodeDetailVo
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 */
	@Override
	public CodeDetailVo retrieveCodeDetail(CodeDetailVo codeDetailVo) throws Exception {
		return codeDao.selectCodeDetail(codeDetailVo);
	}




	/* 상세 코드 관리 */
	/**
	 *  @Description  코드 상세 등록
	 *  @method 	  createCodeDetail
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  int
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int createCodeDetail(CodeDetailVo codeDetailVo) throws Exception {
		return codeDao.insertCodeDetail(codeDetailVo);
	}


	/**
	 *  @Description  코드 상세 수정
	 *  @method 	  updateCodeDetail
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  int
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateCodeDetail(CodeDetailVo codeDetailVo) throws Exception {
		return codeDao.updateCodeDetail(codeDetailVo);
	}


	/**
	 *  @Description  코드 상세 삭제
	 *  @method 	  deleteCodeDetail
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  int
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteCodeDetail(CodeDetailVo codeDetailVo) throws Exception {
		return codeDao.deleteCodeDetail(codeDetailVo);
	}
	
}
