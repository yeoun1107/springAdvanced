/**
 *	File Name			: PracticeApiController.java
 *	File Description	: 연습예제 API 컨트롤러
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-19			yeoun1107			최초작성
 */
package com.study.spring.practice.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.spring.practice.service.PracticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/practice")
@RequiredArgsConstructor
public class PracticeApiController {

    private final PracticeService practiceService;

    /**
	 *  @Description	: RestClient 를 활용한 KRW 환율 조회
	 *  @method			: retrieveKrw
	 *  @Writter		: yeoun1107
	 *  @Date			: 2026-03-19
	 *  @return			: ResponseEntity<Map<String, Object>>
     *  
     *  @EditHistory
     *  Date					Author				Description
     *  --------------------------------------------------------------
     *  2026-03-19			    yeoun1107			최초작성
	 */
	@GetMapping("/restClientEx")
	public ResponseEntity<Map<String, Object>> retrieveKrw() {
        Map<String, Object> result = new HashMap<>();

        try {
            // 1. 서비스 호출을 통한 환율 데이터 수신
            Map<String, Object> exchangeData = practiceService.retrieveKrw();

            // 2. 환율 정보(rates) Map에서 KRW 값 추출
            if (exchangeData != null && exchangeData.containsKey("rates")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> rates = (Map<String, Object>) exchangeData.get("rates");
                Object krwRate = rates.get("KRW");

                result.put("status", "SUCCESS");
                result.put("krw", krwRate);
                log.info("[PracticeApiController] > [retrieveKrw] KRW 환율 추출 성공 ::: {}", krwRate);
            } else {
                result.put("status", "FAIL");
                result.put("message", "환율 데이터를 가져올 수 없습니다.");
            }

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            log.error("[PracticeApiController] > [retrieveKrw] 시 Exception 처리 > e.getMessage ::: {}", e.getMessage());
            result.put("status", "ERROR");
            result.put("message", "시스템 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
	}
    
}
