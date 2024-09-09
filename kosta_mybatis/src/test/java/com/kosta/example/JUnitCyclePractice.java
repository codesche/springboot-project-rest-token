package com.kosta.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCyclePractice {
	@Test
	public void test1() {
		System.out.println("첫번째 테스트");
	}
	
	@Test
	public void test2() {
		System.out.println("두번째 테스트");
	}
	
	// 각각의 테스트를 시작하기 전에 Hello 출력!
	@BeforeEach
	public void hello () {
		System.out.println("Hello");
	}
	// 모든 테스트를 종료하기 전에 Bye 출력!
	@AfterAll
	public static void bye() {
		System.out.println("Bye");
	}
}
