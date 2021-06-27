package com.moneelab.assignment.service.like;

import com.moneelab.assignment.dto.like.LikeResponse;
import com.moneelab.assignment.exception.NotAvailableException;
import com.moneelab.assignment.exception.NotExistException;

import java.util.List;

public interface LikeService {
    Long doLike(Long postId, Long userId) throws NotAvailableException;
    void cancelLike(Long postId, Long userId) throws NotExistException ;
    LikeResponse findOneByPostIdAndUserId(Long postId, Long userId) throws NotExistException;
    LikeResponse findOneById(Long likeId) throws NotExistException;
    List<LikeResponse> findLikesByPostId(Long postId);
}
