package com.linkedin.linkedin_post_service.service;

import com.linkedin.linkedin_post_service.entity.PostLike;
import com.linkedin.linkedin_post_service.exceptions.customexceptions.NotAbleToLikeException;
import com.linkedin.linkedin_post_service.exceptions.customexceptions.PostNotFoundException;
import com.linkedin.linkedin_post_service.exceptions.customexceptions.AlreadyLikedException;
import com.linkedin.linkedin_post_service.repository.PostLikeRepository;
import com.linkedin.linkedin_post_service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeService {
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public String toggleLike(Long postId, Long userId) {
        log.info("Attempting to toggle like for postId = {}, userId = {}", postId, userId);

        // 1. Check if post exists
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException("Post not found with id " + postId);
        }

        // 2. Check like status
        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);

        if (alreadyLiked) {
            // → UNLIKE
            postLikeRepository.deleteByUserIdAndPostId(userId, postId);
            log.info("User {} unliked post {}", userId, postId);
            return "Post unliked successfully";
        } else {
            // → LIKE
            PostLike postLike = new PostLike();
            postLike.setPostId(postId);
            postLike.setUserId(userId);
            postLikeRepository.save(postLike);

            log.info("User {} liked post {}", userId, postId);
            return "Post liked successfully";
        }
    }


}
