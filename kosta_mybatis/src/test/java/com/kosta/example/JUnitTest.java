package com.kosta.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
	
	@DisplayName("1 + 2 = 3 검증") // 테스트 이름 설정
	@Test // 테스트 메소드
	public void jUnitTest() {
		// given
		int a = 1;
		int b = 2;
		int sum = 3;
		
		// when
		int result = a + b;
		
		// then
		Assertions.assertEquals(sum, result); // assertEquals(기대값, 검증값);
	}
	
//	@DisplayName("1 + 3 = 3 검증") // 테스트 이름 설정
//	@Test // 테스트 메소드
//	public void jUnitTest2() {
//		// given
//		int a = 1;
//		int b = 3;
//		int sum = 3;
//		
//		// when
//		int result = a + b;
//		
//		// then
//		Assertions.assertEquals(sum, result); // assertEquals(기대값, 검증값);
//	}
}
