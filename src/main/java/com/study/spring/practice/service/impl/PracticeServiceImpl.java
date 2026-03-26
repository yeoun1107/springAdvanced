/**
 *	File Name			: PracticeServiceImpl.java
 *	File Description	: 연습예제 서비스 구현 클래스
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-19			yeoun1107				최초작성
 */
package com.study.spring.practice.service.impl;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.study.spring.practice.service.PracticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PracticeServiceImpl implements PracticeService {

	/**
	 *  @Description	: RestClient 를 활용한 KRW 환율 조회
	 *  @method			: retrieveKrw
	 *  @Writter		: yeoun1107
	 *  @Date			: 2026-03-19
	 *  @return			: <Map<String, Object>
     *  
     *  @EditHistory
     *  Date					Author				Description
     *  --------------------------------------------------------------
     *  2026-03-19			    yeoun1107			최초작성
	 */
	@Override
	public Map<String, Object> retrieveKrw() throws Exception {
		// 1. RestClient 생성 (간단하게 create 사용)
		RestClient restClient = RestClient.create();

		// 2. 외부 API 호출 및 데이터 수신
		Map<String, Object> response = restClient.get()
				.uri("https://open.er-api.com/v6/latest/USD")
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
					log.info("[클라이언트 에러] :: 요청 재확인 바랍니다.(요청주소 포함) {}", res.getStatusText());
				})
				.onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
					log.info("[서버 에러]  :: 상대 서버에 문제가 있습니다. {}", res.getStatusText());
				})
				.body(new ParameterizedTypeReference<Map<String, Object>>() {});

		// 3. 로그 기록
		log.info("[PracticeServiceImpl] > [retrieveKrw] 환율 데이터 수신 완료 ::: {}", response);

		return response;
	}
}
