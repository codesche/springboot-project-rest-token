package com.kosta.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.kosta.entity.Article;

@DataJpaTest // JPA 관련 테스트
@AutoConfigureTestDatabase(replace = Replace.NONE) // 실제 DB 사용
public class BlogRepositoryTest {
	@Autowired
	private BlogRepository blogRepository;
	
	// 테스트 코드 패턴 : Given(데이터 주고) - When(테스트) - Then(검증)
	
	@Test
	@DisplayName("게시글 추가 테스트")
	public void saveArticleTest() {
		// G
		Article article = Article.builder().title("TEST title").content("TEST content").build();
		
		// W
		Article savedArticle = blogRepository.save(article);
		
		// T
		assertThat(savedArticle).isNotNull();
		assertThat(savedArticle.getId()).isNotNull();
		assertThat(savedArticle.getTitle()).isEqualTo("TEST title");
		assertThat(savedArticle.getContent()).isEqualTo("TEST content");
	}
	
	@Test
	@DisplayName("게시글 전체 조회 테스트")
	public void findAllTest() {
		// G
		Article article1 = Article.builder().title("제목1").content("내용1").build();
		blogRepository.save(article1);
		
		Article article2 = Article.builder().title("제목2").content("내용2").build();
		blogRepository.save(article2);
		
		// W
		List<Article> articleList = blogRepository.findAll();
		
		// T
		assertThat(articleList).isNotNull();
		assertThat(articleList.size()).isGreaterThanOrEqualTo(2);
		assertThat(articleList.stream().anyMatch(article -> article.getTitle().equals("제목1"))).isTrue();
		assertThat(articleList.stream().anyMatch(article -> article.getContent().equals("내용2"))).isTrue();
	}
	
	@Test
	@DisplayName("특정 게시물 조회(ID)")
	public void findByIdTest() {
		// G
		Article article = Article.builder().title("새로운 글 제목").content("새로운 글 내용").build();
		Article savedArticle = blogRepository.save(article);
		
		// W
		// Optional<Article> optArticle = blogRepository.findById(savedArticle.getId());
		// Article foundArticle = optArticle.get();
		Article foundArticle = blogRepository.findById(savedArticle.getId()).get();
		
		// T
		assertThat(foundArticle).isNotNull();
		assertThat(foundArticle.getId()).isEqualTo(savedArticle.getId());
		assertThat(foundArticle.getTitle()).isEqualTo(savedArticle.getTitle());
		assertThat(foundArticle.getContent()).isEqualTo(savedArticle.getContent());
	}
	
	@Test
	@DisplayName("특정 게시물 삭제 (ID)")
	public void deleteArticleById() {
		// G
		int originSize = blogRepository.findAll().size();
		Article article = Article.builder().title("새로운 글 제목").content("새로운 글 내용").build();
		Article savedArticle = blogRepository.save(article);
		
		// W
		blogRepository.deleteById(savedArticle.getId());
		int newSize = blogRepository.findAll().size();
		
		// T
		assertThat(originSize).isEqualTo(newSize);
	}
	
	@Test
	@DisplayName("특정 게시물 수정")
	public void updateArticle() {
		// G
		Article article = Article.builder().title("새로운 글 제목").content("새로운 글 내용").build();
		Article savedArticle = blogRepository.save(article);
		
		// W
		Article foundArticle = blogRepository.findById(savedArticle.getId()).get();
		foundArticle.setTitle("변경된 글 제목");
		foundArticle.setContent("변경된 글 내용");
		
		// T
		Article changedArticle = blogRepository.findById(savedArticle.getId()).get();
		assertThat(foundArticle.getTitle()).isEqualTo(changedArticle.getTitle());
		assertThat(foundArticle.getContent()).isEqualTo(changedArticle.getContent());
	}
	
	@Test
	@DisplayName("제목 또는 내용 검색 & 정렬")
	public void searchByTitleOrContent() {
		// G
		Article article1 = Article.builder().title("에스파 - Supernova").content("슈슈슈슈슈퍼노바").build();
		Article savedArticle1 = blogRepository.save(article1);
		Article article2 = Article.builder().title("난 그게 좋던데").content("에스파 넥스트네벌").build();
		Article savedArticle2 = blogRepository.save(article2);
		
		String keyword = "에스파";
		
		// W
		List<Article> resultList = blogRepository.findByTitleContainsOrContentContainsOrderByTitleAsc(keyword, keyword);
		
		// T
		assertThat(resultList.indexOf(savedArticle1)).isGreaterThan(resultList.indexOf(savedArticle2));
		assertThat(resultList.stream().allMatch(article -> {
			return article.getTitle().contains(keyword) || article.getContent().contains(keyword);
		})).isTrue();
	}
}
