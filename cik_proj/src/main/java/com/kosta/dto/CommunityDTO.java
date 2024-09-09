package com.kosta.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class CommunityDTO {
	private int id;
	private String title, content;
	private int hit;
	private LocalDateTime createdAt;
	private UserDTO creator;
	private List<FileDTO> fileList;
}
