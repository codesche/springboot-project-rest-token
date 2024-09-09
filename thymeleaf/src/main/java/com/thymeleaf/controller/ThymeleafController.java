package com.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thymeleaf.dto.UserDTO;

@Controller
public class ThymeleafController {
	@RequestMapping("/")
	public ModelAndView showMainPage() {
		ModelAndView mav = new ModelAndView("main");
		return mav;
	}
	
	
	
	
	// 타임리프는 자바 기반의 서버 템플릿 엔진이다.
	// HTML 파일에서 서버 데이터를 표현하고 조작할 수 있도록 도와준다.
	@RequestMapping("/variable")
	public ModelAndView varExpression() {
		ModelAndView mav = new ModelAndView("var");
		mav.addObject("variable", "변수 데이터");
		mav.addObject("variable2", "<strong>변수 데이터2</strong>");
		
		UserDTO userDTO = new UserDTO(1, "최인규", "서울 성북구");
		mav.addObject("user", userDTO);
		
		return mav;
	}
	
	
	@RequestMapping("/search")
	public ModelAndView searchResult(@RequestParam("keyword") String keyword) {
		ModelAndView mav = new ModelAndView("result");
		mav.addObject("keyword", keyword);
		
		List<UserDTO> userList = new ArrayList<>();
		userList.add(new UserDTO(2, "서영훈", "서울"));
		userList.add(new UserDTO(3, "정서연", "대전"));
		userList.add(new UserDTO(4, "박병찬", "대구"));
		userList.add(new UserDTO(5, "김태환", "부산"));
		userList.add(new UserDTO(5, "한민혁", "서울"));
		
		mav.addObject("list", userList);
		return mav;
	}
}
