package com.moneelab.assignment.service.like;

import com.moneelab.assignment.domain.like.Like;
import com.moneelab.assignment.domain.like.LikeRepository;

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
    public Long doLike(Long postId, Long userId) {
        Like like = Like.createLike(postId, userId);
        return likeRepository.save(like);
    }

    @Override
    public void cancelLike(Long postId, Long userId) {
        likeRepository.delete(postId, userId);
    }
}
