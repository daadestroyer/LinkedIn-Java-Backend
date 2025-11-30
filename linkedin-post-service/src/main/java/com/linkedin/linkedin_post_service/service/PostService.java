package com.linkedin.linkedin_post_service.service;

import com.linkedin.linkedin_post_service.dto.PostCreateRequestDto;
import com.linkedin.linkedin_post_service.dto.PostDto;
import com.linkedin.linkedin_post_service.entity.Post;
import com.linkedin.linkedin_post_service.exceptions.customexceptions.PostNotFoundException;
import com.linkedin.linkedin_post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, long userId) {
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    public Post getPostById(Long postId) {
        return postRepository
                .findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id : " + postId));

    }

    public List<PostDto> getAllPostOfUser(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return posts
                .stream()
                .map((element) -> modelMapper.map(element, PostDto.class))
                .collect(Collectors.toList());
    }
}