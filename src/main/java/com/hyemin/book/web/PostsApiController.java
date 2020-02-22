package com.hyemin.book.web;

import com.hyemin.book.domain.posts.Posts;
import com.hyemin.book.service.posts.PostsService;
import com.hyemin.book.web.dto.PostsResponseDto;
import com.hyemin.book.web.dto.PostsSaveRequestDto;
import com.hyemin.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Proxy;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;


    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto postsSaveRequestDto) {
        return postsService.save(postsSaveRequestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto) {
        return postsService.update(id, postsUpdateRequestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("api/v1/posts/{id}")
    public Long delete(@PathVariable Long id)
    {
        postsService.delete(id);
        return id;
    }
}


