package com.study.spring.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.study.spring.common.constants.ResponseCode;
import com.study.spring.common.model.CommonResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 *	File Name			: GlobalExceptionHandler.java
 *	File Description	: 전역 에러 처리 핸들러  :  전체 프로젝트의 모든 컨트롤러 감시(Audit) + 응답은 자동 JSON 변환(Rest)
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-19			yeoun1107			최초작성
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    // 모든 Exception 을 여기서 잡아서 처리 (가장 넓은 최상위 범위)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)       // 시스템 에러 이므로 HTTP 상태는 500 으로 응답해서 AJAX 'error' 블록에서 처리하게 함
    public CommonResponseDto<Void> handleException(Exception e) {
        log.error("시스템 전역 에러 발생 >>>>>> {}", e.getMessage(), e);

        // 보안을 위해 상세 메시지는 숨기고 Enum 으로 설정한 SERVER_ERROR 의 기본 메시지만 전송
        // return CommonResponseDto.fail(ResponseCode.SERVER_ERROR, e.getMessage());
        return CommonResponseDto.fail(ResponseCode.SERVER_ERROR);
    }



    // 에러 Custom 처리
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.OK)              // 통신 자체는 정상적인 비즈니스 예외에 대한 커스텀 에러 처리이므로 HTTP 상태는 200(OK) 로 응답해서 AJAX 'success' 블록에서 처리하게 함
    public CommonResponseDto<Void> handleCustomException(CustomException e) {
        log.warn("전역 커스텀 에러 (예상 가능한 비즈니스 로직 예외) 처리 >>>>>> {}", e.getCustomMessage(), e);

        return CommonResponseDto.fail(ResponseCode.SERVER_ERROR, e.getCustomMessage());
    }


    
    // 정적 리소스 에러 제외 처리
    // 화면 이동 및 페이지 호출 시 브라우저에서 favicon.io 를 서버에 요청하는데 서버에 현재 이 파일이 없으므로 NoResourceFoundException 을 던져 전역처리 ExceptionHandler에 걸리므로
    // 이를 전역처리기가 잡지 않도록 하기 위해 생성.
    // 이거 생성 안하려면 resources/static 폴더 안에 아무 아이콘 파일이나 favicon.io 이름으로 넣어두면 된다.
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public void handleNoResourceFoundException(Exception e) {
        // 로그를 남기지 않거나, 아주 낮은 단계(trace/debug)로만 남깁니다.
        // 리턴을 void로 하면 스프링이 알아서 404를 내보냅니다.
    }
    
}
