package com.kosta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.dto.CommunityDTO;
import com.kosta.dto.FileDTO;

@Mapper
public interface CommunityMapper {

	List<CommunityDTO> selectAllCommunity();

	void insertCommunity(CommunityDTO commDTO);

	CommunityDTO selectCommunityById(int id);

	void updateHit(int id);
	
	void updateCommunity(CommunityDTO commDTO);
	
	void deleteCommunity(int id);
	
	List<FileDTO> selectAllFileByCommunityId(int id);

	void insertFile(List<FileDTO> fileList);

	void deleteFileByCommunityId(int id);

}
