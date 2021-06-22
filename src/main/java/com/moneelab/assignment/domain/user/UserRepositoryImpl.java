package com.moneelab.assignment.domain.user;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class UserRepositoryImpl implements UserRepository {

    private static ConcurrentHashMap<Long, User> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    /**
     * making Singleton
     */
    private static final UserRepositoryImpl instance = new UserRepositoryImpl();

    public static UserRepositoryImpl getInstance() {
        return instance;
    }

    /**
     * manipulating domain object
     */
    @Override
    public User save(User user) {
        user.initId(++sequence);
        return store.put(user.getId(), user);
    }

    @Override
    public User findById(Long userId) {
        return store.get(userId);
    }

    @Override
    public User findByName(String username) {
        AtomicReference<User> result = null;

        store.values().iterator()
                .forEachRemaining(user -> {
                    if (user.getName().equals(username)) {
                        result.set(user);
                    }
                });

        return result.get();
    }
}
