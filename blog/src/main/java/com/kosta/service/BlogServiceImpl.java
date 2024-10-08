package com.kosta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.entity.Article;
import com.kosta.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

	private final BlogRepository blogRepository;

	@Override
	public Article save(Article article) {
		return blogRepository.save(article);
	}

	@Override
	public List<Article> findAll() {
		return blogRepository.findAll();
	}

	@Override
	public Article findById(Long id) throws Exception {
		Optional<Article> optArticle = blogRepository.findById(id);
		Article article = optArticle.orElseThrow(() -> new Exception("ID가 없습니다."));
		return article;
	}

	@Override
	public void deleteById(Long id) throws Exception {
		Article article = findById(id);
		blogRepository.deleteById(article.getId());
	}

	@Override
	public Article update(Article article) throws Exception {
		Article originArticle = findById(article.getId());
		originArticle.setTitle(article.getTitle());
		originArticle.setContent(article.getContent());
		
		Article updatedArticle = blogRepository.save(originArticle);
		return updatedArticle;
	}

	@Override
	public List<Article> searchAndOrder(String keyword, String order) {
		if (order.equals("desc")) {
			// 내림차순 결과 반환
			return blogRepository.findByTitleContainsOrContentContainsOrderByTitleDesc(keyword, keyword);
		} 
		// 오른차순 결과 반환
		return blogRepository.findByTitleContainsOrContentContainsOrderByTitleAsc(keyword, keyword);
	}
}
