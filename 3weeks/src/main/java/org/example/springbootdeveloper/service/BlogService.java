package org.example.springbootdeveloper.service;
import jakarta.transaction.Transactional;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.dto.AddArticleRequest;
import org.example.springbootdeveloper.dto.UpdateArticleRequest;
import org.example.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor//final이 붙거나 @NotNull 이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService{
    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request)
    {
        return blogRepository.save(request.toEntity());

    }
    public List<Article> findAll()
    {
        return blogRepository.findAll();
        //Article table저장되어 있는 모든 데이터 조회
    }
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: " + id));
        //JPA에서 제공하는 findById 사용하여 Id받아 엔티티 조회하고 없으면 예외 처리

    }

    public void delete(long id)
    {
        blogRepository.deleteById(id);
    }

    @Transactional // 트랜잭션 메서드
    //매칭한 메서드를 하나의 트랜잭션으로 묶는 역할
    public Article update(long id, UpdateArticleRequest request){
        Article article=blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+ id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }

}