package com.kosta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.dto.CommunityDTO;
import com.kosta.dto.UserDTO;
import com.kosta.service.CommunityService;
import com.kosta.service.UserService;

@Controller
@RequestMapping("/community")
public class CommunityController {
	@Autowired
	CommunityService cs;
	@Autowired
	UserService us;
	
	@RequestMapping(value = { "", "/", "/list" })
	public ModelAndView showCommList() throws Exception {
		ModelAndView mav = new ModelAndView("community/list");
		// 게시글 전체 가져오기
		List<CommunityDTO> communityList = cs.getAllCommunityPosts();
		System.out.println(communityList);
		mav.addObject("list", communityList);
		return mav;
	}
	
	// 게시글 글쓰기 화면
	@GetMapping("/write")
	public ModelAndView showCommWriteForm() throws Exception {
		ModelAndView mav = new ModelAndView("community/write");
		List<UserDTO> userList = us.getAllUserList();
		mav.addObject("userList", userList);
		return mav;
	}

	// 게시글 글쓰기
	@PostMapping("/write")
	public String writeCommPost(CommunityDTO commDTO, @RequestParam("creator_id") int id, @RequestParam("files") List<MultipartFile> files) throws Exception {
		UserDTO creator = us.getUserById(id);
		commDTO.setCreator(creator);
		// 게시글 등록하기
		cs.addCommunityPost(commDTO, files);
		return "redirect:/community";
	}

	// 게시글 삭제
	@DeleteMapping("/delete")
	public String deleteCommPost(@RequestParam("id") int id) throws Exception {
		// 게시글 삭제하기
		cs.deleteCommunityPost(id);
		return "redirect:/community";
	}

	// 게시글 수정 화면
	@GetMapping("/modify")
	public ModelAndView showCommModifyForm(@RequestParam("id") int id) throws Exception {
		ModelAndView mav = new ModelAndView("community/write");
		// 게시글 정보 가져오기
		CommunityDTO commDTO = cs.getCommunityPost(id);
		mav.addObject("coummunity", commDTO);
		return mav;
	}

	// 게시글 수정
	@PatchMapping("/modify")
	public String modifyCommPost(CommunityDTO commDTO, @RequestParam("files") List<MultipartFile> files) throws Exception {
		// 게시글 수정하기
		cs.modifyCommunityPost(commDTO, files);
		return "redirect:/community";
	}

	// 게시글 상세보기 화면
	@GetMapping("/detail/{id}")
	public ModelAndView showCommDetail(@PathVariable("id") int id) throws Exception {
		ModelAndView mav = new ModelAndView("community/detail");
		CommunityDTO commDTO = cs.getCommunityPost(id);
		mav.addObject("community", commDTO);
		return mav;
	}

	// 파일 이미지 다운로드
}
