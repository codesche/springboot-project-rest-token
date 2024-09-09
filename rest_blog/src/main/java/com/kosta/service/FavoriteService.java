package com.kosta.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.domain.FavoriteRequest;
import com.kosta.domain.FavoriteResponse;

public interface FavoriteService {

	FavoriteResponse insertFav(FavoriteRequest fav, MultipartFile file);

	List<FavoriteResponse> getAllFav();

	FavoriteResponse updateFav(FavoriteRequest fav, MultipartFile file);

	FavoriteResponse deleteFav(Long id);

}
