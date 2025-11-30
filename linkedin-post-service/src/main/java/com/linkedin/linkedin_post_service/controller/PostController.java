package com.linkedin.linkedin_post_service.controller;

import com.linkedin.linkedin_post_service.dto.PostCreateRequestDto;
import com.linkedin.linkedin_post_service.dto.PostDto;
import com.linkedin.linkedin_post_service.entity.Post;
import com.linkedin.linkedin_post_service.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest){
        PostDto createdPost = postService.createPost(postCreateRequestDto,1L);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId){
        Post post = postService.getPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/allPost")
    public ResponseEntity<?> getAllPostOfUser(@PathVariable Long userId){
        List<PostDto> postDtoList = postService.getAllPostOfUser(userId);
        if(postDtoList.isEmpty()){
            return new ResponseEntity<>("No post found for user id = "+userId,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
}
