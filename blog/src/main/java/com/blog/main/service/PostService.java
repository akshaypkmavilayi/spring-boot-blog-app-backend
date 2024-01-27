package com.blog.main.service;

import com.blog.main.dto.PostDto;

import java.util.List;

public interface PostService {
    void createPost(PostDto postDto);

    List<PostDto> showAllPosts();

    PostDto readSinglePost(Long id);

    void deletePost(Long id);
}
