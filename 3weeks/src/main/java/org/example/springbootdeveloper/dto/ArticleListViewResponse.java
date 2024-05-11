package org.example.springbootdeveloper.dto;
import lombok.Getter;
import org.example.springbootdeveloper.domain.Article;

@Getter
//뷰에게 데이터 전달하기위하 객체 생성
public class ArticleListViewResponse{
    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article){
        this.id=article.getId();
        this.title=article.getTitle();
        this.content=article.getContent();
    }

}