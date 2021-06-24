package com.moneelab.assignment.domain.user;

import java.util.Optional;

public interface UserRepository {

    User save(User user);
    User findById(Long userId);
    Optional<User> findByName(String username);
}
