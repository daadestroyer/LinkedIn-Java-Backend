package com.linkedin.linkedin_post_service.controller;

import com.linkedin.linkedin_post_service.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> toggleLike(@PathVariable Long postId){
        String message = postLikeService.toggleLike(postId,1L);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
