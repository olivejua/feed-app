package com.moneelab.assignment.service.like;

import com.moneelab.assignment.domain.like.Like;
import com.moneelab.assignment.domain.like.LikeRepository;
import com.moneelab.assignment.dto.like.LikeResponse;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.service.post.PostService;

import java.util.List;
import java.util.stream.Collectors;

import static com.moneelab.assignment.config.AppConfig.*;
import static com.moneelab.assignment.config.AppConfig.likeRepository;

public class LikeServiceImpl implements LikeService {

    /**
     * invoking a repository instance
     */
    private LikeRepository likeRepository = likeRepository();
    private PostService postService = postService();

    /**
     * making it Singleton
     */
    private LikeServiceImpl() {}
    private static final LikeServiceImpl instance = new LikeServiceImpl();

    public static LikeServiceImpl getInstance() {
        return instance;
    }

    /**
     * processing business logic
     */
    @Override
    public synchronized Long doLike(Long postId, Long userId) {
        Like like = Like.createLike(postId, userId);
        return likeRepository.save(like);
    }

    @Override
    public synchronized void cancelLike(Long postId, Long userId) throws NotExistException {
        findOneByPostIdAndUserId(postId, userId);
        likeRepository.delete(postId, userId);
    }

    @Override
    public LikeResponse findOneByPostIdAndUserId(Long postId, Long userId) throws NotExistException {
        postService.findById(postId);

        Like like = likeRepository.findLikeByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new NotExistException("좋아요한 이력이 없습니다. postId=" + postId + ", userId=" + userId));

        return new LikeResponse(like);
    }

    @Override
    public synchronized List<LikeResponse> findLikesByPostId(Long postId) {
        return likeRepository.findLikesByPostId(postId).stream()
                .map(LikeResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public LikeResponse findOneById(Long likeId) throws NotExistException {
        Like like = likeRepository.findOneById(likeId)
                .orElseThrow(() -> new NotExistException("좋아요한 이력이 없습니다. likeId=" + likeId));

        return new LikeResponse(like);
    }

}
