package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 해당 클래스가 REST CONTROLLER 기능을 수행하도록 한다.
public class HelloController {
	@RequestMapping("/") // 메소드가 실행할 수 있는 주소(경로) 설정
	public String hello() {
		return "Hello world";
	}
	@RequestMapping("/hello") // 메소드가 실행할 수 있는 주소(경로) 설정
	public String hello2() {
		return "헬로에 들어왔네!!!";
	}
}
