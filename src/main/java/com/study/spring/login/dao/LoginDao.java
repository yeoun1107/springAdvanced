package com.study.spring.login.dao;

import org.apache.ibatis.annotations.Mapper;
import com.study.spring.login.vo.LoginVo;
import java.util.Map;

/**
 *	File Name			: LoginDao.java
 *	File Description	: 로그인 및 회원가입 데이터 접근 인터페이스
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-12			yeoun1107				최초작성
 * 	2026-03-16			yeoun1107				username -> userId 컬럼 변경 대응
 */
@Mapper
public interface LoginDao {

	// 로그인 사용자 조회 샘플
	Map<String, Object> selectLogin() throws Exception;

	// 아이디 중복 체크 (사용자 수 조회)
	int selectCountUserId(String userId) throws Exception;

	// 로그인 정보 기반 사용자 조회
	LoginVo selectUserForLogin(LoginVo loginVo) throws Exception;

	// 회원가입 정보 저장
	void insertUser(LoginVo loginVo) throws Exception;
}
