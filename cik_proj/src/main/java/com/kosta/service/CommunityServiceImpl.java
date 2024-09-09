package com.kosta.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.dto.CommunityDTO;
import com.kosta.dto.FileDTO;
import com.kosta.mapper.CommunityMapper;

@Service
public class CommunityServiceImpl implements CommunityService {
	@Autowired
	private CommunityMapper cm;

	@Override
	public List<CommunityDTO> getAllCommunityPosts() throws Exception {
		return cm.selectAllCommunity();
	}

	@Override
	public void addCommunityPost(CommunityDTO commDTO, List<MultipartFile> files) throws Exception {
		cm.insertCommunity(commDTO);
		int commId = commDTO.getId();

		// 첨부파일이 있으면 저장
		if (files != null && !files.isEmpty()) {
			List<FileDTO> fileList = new ArrayList<>();
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					// 원본 파일명
					String originFileName = file.getOriginalFilename();
					// 새로운 파일명
					String newFileName = UUID.randomUUID().toString() + "_" + originFileName;
					String storedFilePath= "C:\\Users\\WD\\community_file\\" + newFileName;
					// 파일 크기
					long fileSize = file.getSize();
					
					FileDTO fileDTO = new FileDTO();
					fileDTO.setCommunityId(commId);
					fileDTO.setOriginFileName(originFileName);
					fileDTO.setStoredFilePath(storedFilePath);
					fileDTO.setFileSize(fileSize);
					
					fileList.add(fileDTO);
					
					File dest = new File(storedFilePath);
					file.transferTo(dest);
				}
			}
			// fileTbl에 추가
			if (!fileList.isEmpty()) cm.insertFile(fileList);
		}
	}

	@Override
	public void deleteCommunityPost(int id) throws Exception {
		// TODO Auto-generated method stub
		cm.deleteCommunity(id);
	}

	@Override
	public CommunityDTO getCommunityPost(int id) throws Exception {
		CommunityDTO commDTO = cm.selectCommunityById(id);
		List<FileDTO> fileList = cm.selectAllFileByCommunityId(id);
		commDTO.setFileList(fileList);
		cm.updateHit(id);
		return commDTO;
	}

	@Override
	public void modifyCommunityPost(CommunityDTO commDTO, List<MultipartFile> files) throws Exception {
		cm.updateCommunity(commDTO);
		// 첨부파일이 있으면 저장

		// fileTbl에 추가
		List<FileDTO> fileList = new ArrayList<>();
		cm.deleteFileByCommunityId(commDTO.getId());
		cm.insertFile(fileList);
	}

}
