package com.hyemin.book.service.posts;

import com.hyemin.book.domain.posts.JpaRepository;
import com.hyemin.book.domain.posts.Posts;
import com.hyemin.book.web.dto.PostsListResponseDto;
import com.hyemin.book.web.dto.PostsResponseDto;
import com.hyemin.book.web.dto.PostsSaveRequestDto;
import com.hyemin.book.web.dto.PostsUpdateRequestDto;
import javafx.geometry.Pos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final JpaRepository jpaRepository;

    @Transactional
    public Long save(PostsSaveRequestDto postsSaveRequestDto)
    {
        return jpaRepository.save(postsSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto)
    {
        Posts posts=jpaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다" +
                "id="+id));

        posts.update(postsUpdateRequestDto.getTitle(),postsUpdateRequestDto.getContent());
        return id;
    }
    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id)
    {
        Posts entity = jpaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다." +
                "id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc()
    {
        return jpaRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id)
    {
        Posts posts = jpaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다 id="+id));
        jpaRepository.delete(posts);
    }
}
