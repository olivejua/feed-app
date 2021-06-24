package com.moneelab.assignment.domain.user;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryImpl implements UserRepository {

    /**
     * store: In-memory DB for User
     * sequence: To manage auto increment id values
     */
    private static ConcurrentHashMap<Long, User> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    /**
     * making it Singleton
     */
    private UserRepositoryImpl() {}
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
        store.put(user.getId(), user);

        return user;
    }

    @Override
    public User findById(Long userId) {
        return store.get(userId);
    }
    
    @Override
    public Optional<User> findByName(String username) {
        return store.values().stream()
                .filter(user -> user.getName().equals(username))
                .findAny();
    }
}
