package org.example.springbootdeveloper.controller;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.dto.AddArticleRequest;
import org.example.springbootdeveloper.dto.ArticleResponse;
import org.example.springbootdeveloper.dto.UpdateArticleRequest;
import org.example.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
//빈을 생성자로 생성하는 롬복에서 지원
//final 이나 @NotNull이 붙은 필드로 생성자를 만들어줌
@RestController
// Http응답으로 객체데이터를 JSON 형식으로 반환
public class BlogApiController{

    private final BlogService blogService;

    //HTTP 메서드가 POST일 때 전달받은 URL와 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    //HTTP 메서드가 POST일 떄 요청받은 URL와 동일한 메서드 매핑
    //요청 본문 값 매핑
    //Http 요청할 떄 응답에 해당하는 값을 @RequestBody 애너테이션이 붙으 대상 객체인
    //AddArticleRequest 에 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        //요청한 자원이 성공적으로 생성되었으며 저장한 블로그 글 정보를 응답객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);


    }
    @GetMapping("/api/articles")
    //  findAll메서드 호출후 응답용 객체인 AritcleREsponse로 파싱해 body에 담아 클라이언트에게 전송
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles  =blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }
    @GetMapping("/api/articles/{id}")
    //Url 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        //Pathvariable 애너테이션은 url에서 값을 가져오는 애너테이션. get요청을 받으면 id에 값이 들어오고
        //이 값이 findById 메서드로 넘어가 id 번에 해당하는 블로그 글찾음 찾으면 body에담아 웹브라우저에 전송
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id)
    {
        blogService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request){
        Article updateArticle=blogService.update(id,request);
        return ResponseEntity.ok()
                .body(updateArticle);
    }






}