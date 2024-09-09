package com.kosta.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.dto.CommunityDTO;

public interface CommunityService {
	// 게시글 전체 가져오기 (getAllCommunityPosts)
	List<CommunityDTO> getAllCommunityPosts() throws Exception;
	// 게시글 등록하기 (addCommunityPost)
	void addCommunityPost(CommunityDTO commDTO, List<MultipartFile> files) throws Exception;
	// 게시글 삭제하기 (deleteCommunityPost)
	void deleteCommunityPost(int id) throws Exception;
	// 게시글 정보 가져오기 (getCommunityPost)
	CommunityDTO getCommunityPost(int id) throws Exception;
	// 게시글 수정하기 (modifyCommunityPost)
	void modifyCommunityPost(CommunityDTO commDTO, List<MultipartFile> files) throws Exception;
	
}
