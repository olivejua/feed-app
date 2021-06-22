package com.moneelab.assignment.domain.user;

public interface UserRepository {

    User save(User user);
    User findById(Long userId);
    User findByName(String username);
}
