package com.hyemin.book.domain.posts;

import javafx.geometry.Pos;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaRepositoryTest {

    @Autowired
    JpaRepository jpaRepository;

    @After
    public void cleanup()
    {
        jpaRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기()
    {
        String title = "테스트 게시글";
        String content="테스트 본문";

        jpaRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("aaa@aaa")
        .build());

        List<Posts> postsList = jpaRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록()
    {
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        jpaRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        //when
        List<Posts> postsList = jpaRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>createDate ="+posts.getCreatedDate() +",modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }

}