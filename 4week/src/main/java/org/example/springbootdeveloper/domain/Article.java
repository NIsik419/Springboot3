package org.example.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.annotation.processing.Generated;

import java.time.LocalDateTime;
import java.util.*;
@Entity //엔티티로 지정
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본키 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)//'title'이라는 not null 컬럼과 매핑
    private String title;
    @Column(name = "content", nullable = false)
    private String content;

    @Builder // 빌더 패턴으로 객체 생성
    //생성자 위에 입력시 빌더패턴 방식으로 객체 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public void update(String title, String content)
    {
        this.title=title;
        this.content=content;
    }
    @CreatedDate // 엔티티 생성될떄 시간 저장
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @LastModifiedDate//엔티티 수정시간 저장
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

// Buillder 애너테이션과 다른 애너테이션 덕분에 생략가능
//    protected Article() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//}



}