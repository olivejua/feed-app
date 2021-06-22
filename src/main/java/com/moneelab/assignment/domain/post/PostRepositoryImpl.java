package com.moneelab.assignment.domain.post;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PostRepositoryImpl implements PostRepository {

    private static ConcurrentHashMap<Long, Post> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    /**
     * making it Singleton
     */
    private PostRepositoryImpl() {}
    private static final PostRepositoryImpl instance = new PostRepositoryImpl();

    public static PostRepositoryImpl getInstance() {
        return instance;
    }


    /**
     * manipulating domain object
     */
    public Post save(Post post) {
        post.initId(++sequence);
        return store.put(post.getId(), post);
    }

    public void update(Post post) {
        post.update(post.getTitle(), post.getContent());
    }

    public void deleteById(Long postId) {
        store.remove(postId);
    }

    public Post findById(Long postId) {
        return store.get(postId);
    }

    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearAll() {
        store.clear();
    }
}
