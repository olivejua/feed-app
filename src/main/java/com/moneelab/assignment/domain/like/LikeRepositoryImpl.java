package com.moneelab.assignment.domain.like;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class LikeRepositoryImpl implements LikeRepository {

    /**
     * store: In-memory DB for Like
     * sequence: To manage auto increment id values
     */
    private static ConcurrentHashMap<Long, Like> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    /**
     * making it Singleton
     */
    private LikeRepositoryImpl() {}
    private static final LikeRepositoryImpl instance = new LikeRepositoryImpl();

    public static LikeRepositoryImpl getInstance() {
        return instance;
    }

    /**
     * manipulating domain object
     */
    @Override
    public Long save(Like like) {
        like.initId(++sequence);
        store.put(like.getId(), like);

        return like.getId();
    }

    @Override
    public void delete(Long postId, Long userId) {
        findLike(postId, userId)
                .ifPresent(like -> store.remove(like.getId()));
    }

    @Override
    public Optional<Like> findLike(Long postId, Long userId) {
        return store.values().stream()
                .filter(like ->
                        like.getPostId().equals(postId) &&
                                like.getUserId().equals(userId))
                .findAny();
    }

    @Override
    public List<Like> findLikesByPostId(Long postId) {
        return store.values().stream()
                .filter(like -> like.getPostId().equals(postId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Like> findLikeByPostIdAndUserId(Long postId, Long userId) {
        return store.values().stream()
                .filter(like -> like.getPostId().equals(postId) && like.getUserId().equals(userId))
                .findAny();
    }

    @Override
    public Optional<Like> findOneById(Long likeId) {
        return store.values().stream()
                .filter(like -> like.getId().equals(likeId))
                .findAny();
    }
}
