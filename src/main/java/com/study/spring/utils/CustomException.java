package com.study.spring.utils;

import com.study.spring.common.constants.ResponseCode;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ResponseCode responseCode;
    private final Object data;
    private final String customMessage;

    
    // 데이터 없이 에러 코드 Enum만 전송
    public CustomException(ResponseCode responseCode) {

        // 추후에 e.getMessage() 로 꺼내기 위해 RuntimeException 에 에러메시지 전달
        super(responseCode.getMessage());

        // 추후에 GlobalExceptionHandler에서 꺼내 쓰기 위해 CustomException 의 변수에 Enum 정보 저장
        this.responseCode = responseCode;
        this.data = null;
        this.customMessage = "";
    }


    // 에러 코드 Enum + 커스텀 메시지 전송 (오버로딩)
    public CustomException(ResponseCode responseCode, String customMessage) {

        // 부모인 RuntimeException 에도 우리가 정한 커스텀 메시지를 알려줘야 e.getMessage() 호출 시에도 우리가 원하는 메시지가 출력
        super(customMessage);

        // 추후에 GlobalExceptionHandler에서 꺼내 쓰기 위해 CustomException 의 변수에 Enum 정보 저장
        this.responseCode = responseCode;
        this.data = null;
        this.customMessage = customMessage;
    }



    // 에러 코드 Enum + 데이터 + 커스텀 메시지 전송 (오버로딩)
    public CustomException(ResponseCode responseCode, Object data, String customMessage) {

        // 부모인 RuntimeException 에도 우리가 정한 커스텀 메시지를 알려줘야 e.getMessage() 호출 시에도 우리가 원하는 메시지가 출력
        super(customMessage);

        this.responseCode = responseCode;
        this.data = data;
        this.customMessage = customMessage;
    }
    
}
