package com.study.spring.login.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.spring.common.constants.ResponseCode;
import com.study.spring.login.dao.LoginDao;
import com.study.spring.login.model.LoginRequestDto;
import com.study.spring.login.service.LoginService;
import com.study.spring.login.vo.LoginVo;
import com.study.spring.utils.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *	File Name			: LoginServiceImpl.java
 *	File Description	: 로그인 및 회원가입 비즈니스 로직 구현 클래스
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-12			yeoun1107				최초작성
 * 	2026-03-16			yeoun1107				username -> userId 컬럼 변경 대응
 *  2026-03-25			yeoun1107				전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final LoginDao loginDao;


	/**
     * @Description 아이디 중복 여부 확인
     * @method 		existUserId
     * @Writter 	yeoun1107
     * @Date 		2026-03-12
	 * @param 		userId
	 * @return 		boolean (true: 중복, false: 미중복)
     * @throws 		Exception
     * 
     * @EditHistory
     * Date					Author				Description
     * --------------------------------------------------------------
     * 2026-03-12			yeoun1107			최초작성
     * 2026-03-13			yeoun1107			VO 매핑 및 날짜 포맷팅(yyyyMMddHHmmss) 적용
     * 2026-03-16			yeoun1107			username -> userId 컬럼 변경 대응
	 * 2026-03-25			yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
     */
	@Override
	public boolean existUserId(String userId) throws Exception {
		log.info("[LoginServiceImpl] > [existUserId] 시 아이디 중복 체크 수행 : {}", userId);
		return loginDao.selectCountUserId(userId) > 0;
	}



	/**
     * @Description 로그인 정보 조회 및 비밀번호 검증
     * @method 		retrieveLogin
     * @Writter 	yeoun1107
     * @Date 		2026-03-12
	 * @param 		loginRequestDto
	 * @return		LoginVo (성공 시 반환)
     * @throws 		Exception
     * 
     * @EditHistory
     * Date					Author				Description
     * --------------------------------------------------------------
     * 2026-03-12			yeoun1107			최초작성
     * 2026-03-13			yeoun1107			VO 매핑 및 날짜 포맷팅(yyyyMMddHHmmss) 적용
     * 2026-03-16			yeoun1107			username -> userId 컬럼 변경 대응
	 * 2026-03-25			yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
     */
	@Override
	public LoginVo retrieveLogin(LoginRequestDto loginRequestDto) throws Exception {
		log.info("[LoginServiceImpl] > [retrieveLogin] 시 로그인 처리 수행");

		// 1. 사용자 정보 조회
		LoginVo searchVo = new LoginVo();
		searchVo.setUserId(loginRequestDto.getUserId());
		
		LoginVo userVo = loginDao.selectUserForLogin(searchVo);

		// 2. 계정 존재 여부 확인
		if (userVo == null) {
			log.warn(" > 존재하지 않는 아이디 : {}", loginRequestDto.getUserId());
			// throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
			throw new CustomException(ResponseCode.BUSINESS_ERROR, null, "아이디 또는 비밀번호를 다시 확인하세요.");
		}

		// 3. 비밀번호 일치 확인 (BCrypt 도입 전까지는 평문 비교)
		if (!userVo.getPassword().equals(loginRequestDto.getPassword())) {
			log.warn(" > 비밀번호 불일치 : {}", loginRequestDto.getUserId());
			throw new CustomException(ResponseCode.BUSINESS_ERROR, null, "아이디 또는 비밀번호를 다시 확인하세요.");
		}

		log.info(" > 로그인 성공 : {}", userVo.getUserId());
		return userVo;
	}



	/**
     * @Description 회원가입 정보 처리 및 DB 저장
     * @method 		createSignup
     * @Writter 	yeoun1107
     * @Date 		2026-03-12
	 * @param 		loginRequestDto
     * @throws 		Exception
     * 
     * @EditHistory
     * Date					Author				Description
     * --------------------------------------------------------------
     * 2026-03-12			yeoun1107			최초작성
     * 2026-03-13			yeoun1107			VO 매핑 및 날짜 포맷팅(yyyyMMddHHmmss) 적용
     * 2026-03-16			yeoun1107			username -> userId 컬럼 변경 대응
	 * 2026-03-27			yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
     */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createSignup(LoginRequestDto loginRequestDto) throws Exception {
		log.info("[LoginServiceImpl] > [createSignup] 시 회원가입 비즈니스 로직 수행");

		// 1. 파라미터 널 체크 (NullPointerException 방지)
		if (loginRequestDto == null) {
			throw new CustomException(ResponseCode.BUSINESS_ERROR, null, "회원가입 요청 데이터가 존재하지 않습니다.");
		}

		if (loginRequestDto.getUserId() == null || loginRequestDto.getUserId().trim().isEmpty()) {
			throw new CustomException(ResponseCode.BUSINESS_ERROR, null, "아이디가 입력되지 않았습니다.");
		}

		if (loginRequestDto.getPassword() == null || loginRequestDto.getPassword().trim().isEmpty()) {
			throw new CustomException(ResponseCode.BUSINESS_ERROR, null, "비밀번호가 입력되지 않았습니다.");
		}

		log.info(" > 전달된 데이터 : {}", loginRequestDto.toString());
		
		// 2. VO 매핑 및 데이터 가공
		LoginVo loginVo = new LoginVo();
		loginVo.setUserId(loginRequestDto.getUserId());
		loginVo.setPassword(loginRequestDto.getPassword()); // BCrypt 암호화는 다음 단계에서 적용 예정
		
		// yyyyMMddHHmmss 형식으로 포맷팅
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		loginVo.setCreateDt(now);
		loginVo.setDelYn("N");

		// 3. DB 저장
		try {
			loginDao.insertUser(loginVo);
		} catch (Exception e) {
			log.error("[LoginServiceImpl] > [createSignup] 시 DB 처리 중 Exception 발생 ::: {}", e.getMessage());
			throw e; // 상위 컨트롤러로 예외 전달
		}
	}


	
}
