package com.hyemin.book.web.dto;


import com.hyemin.book.domain.posts.Posts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //선언된 모든 필드의 get메소드를 생성해준다.
public class PostsResponseDto {
        private Long id;
        private String title;
        private String content;
        private String author;

    public PostsResponseDto(Posts entity){
    this.id=entity.getId();
    this.title=entity.getTitle();
    this.content=entity.getContent();
    this.author=entity.getAuthor();
    }
}
