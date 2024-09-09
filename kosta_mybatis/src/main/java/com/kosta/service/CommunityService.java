package com.kosta.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.dto.Community;
import com.kosta.dto.CommunityFile;

public interface CommunityService {

	void add(Community community, int id, List<MultipartFile> files) throws Exception;

	List<Community> getAllCommunity() throws Exception;

	Community getCommunityById(int id) throws Exception;

	CommunityFile getCommunityFileById(int id) throws Exception;

	void delete(int id) throws Exception;
}
