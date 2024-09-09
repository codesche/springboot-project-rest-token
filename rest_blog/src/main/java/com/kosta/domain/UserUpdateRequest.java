package com.kosta.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserUpdateRequest {
	private String email;
	private String name;
	private String password;
}
