//DTO는 계층끼리 데이터 교환하기 위해 사용되는 객체
package org.example.springbootdeveloper.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest{

    private String title;
    private String content;
    //toEntity 빌더 패턴사용해 DTO를 엔티티로
    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

}