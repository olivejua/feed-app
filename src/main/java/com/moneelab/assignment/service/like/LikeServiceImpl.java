package com.moneelab.assignment.service.like;

import com.moneelab.assignment.domain.like.Like;
import com.moneelab.assignment.domain.like.LikeRepository;
import com.moneelab.assignment.dto.like.LikeResponse;

import java.util.Optional;

import static com.moneelab.assignment.config.AppConfig.likeRepository;

public class LikeServiceImpl implements LikeService {

    /**
     * invoking a repository instance
     */
    private LikeRepository likeRepository = likeRepository();

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
    public synchronized void cancelLike(Long postId, Long userId) {
        likeRepository.delete(postId, userId);
    }

    @Override
    public synchronized LikeResponse findLikeByPostId(Long postId) {
        Optional<Like> optionalLike = likeRepository.findLikeByPostId(postId);
        if (optionalLike.isEmpty()) {
            //TODO
        }

        return new LikeResponse(optionalLike.get());
    }
}
