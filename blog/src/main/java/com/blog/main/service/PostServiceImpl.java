package com.blog.main.service;

import com.blog.main.dto.PostDto;
import com.blog.main.entities.BlogPostEntity;
import com.blog.main.exception.PostNotFoundException;
import com.blog.main.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private AuthService authService;

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public void createPost(PostDto postDto) {
        BlogPostEntity blogPost = new BlogPostEntity();
        blogPost.setBlogTitle(postDto.getTitle());
        blogPost.setBlogContent(postDto.getContent());
        User userName = (User) authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("no user logged in"));
        blogPost.setUserName(userName.getUsername());
        blogPost.setBlogCreatedOn(Instant.now());
        blogRepository.save(blogPost);

    }

    @Override
    public List<PostDto> showAllPosts() {
        List<BlogPostEntity> blogPosts = blogRepository.findAll();
        return blogPosts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }

    private PostDto mapFromPostToDto(BlogPostEntity blogPostEntity) {
        PostDto postDto = new PostDto();
        postDto.setId(blogPostEntity.getUserId());
        postDto.setTitle(blogPostEntity.getBlogTitle());
        postDto.setContent(blogPostEntity.getBlogContent());
        postDto.setUserName(blogPostEntity.getUserName());
        return postDto;
    }

    private BlogPostEntity mapFromDtoToPost(PostDto postDto) {
        BlogPostEntity blogPost = new BlogPostEntity();
        blogPost.setUserId(postDto.getId());
        blogPost.setBlogTitle(postDto.getTitle());
        blogPost.setBlogContent(postDto.getContent());
        blogPost.setUserName(postDto.getUserName());
        return blogPost;
    }

    @Override
    public PostDto readSinglePost(Long id) {
        BlogPostEntity blogPost = blogRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromPostToDto(blogPost);
    }

    @Override
    public void deletePost(Long id) {
       blogRepository.deleteById(id);

    }
}
