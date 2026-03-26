package com.study.spring.login.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginRequestDto {
	private String userId;
	private String password;
}
