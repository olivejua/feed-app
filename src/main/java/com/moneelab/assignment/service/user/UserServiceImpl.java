package com.moneelab.assignment.service.user;

import com.moneelab.assignment.domain.user.User;
import com.moneelab.assignment.domain.user.UserRepository;
import com.moneelab.assignment.domain.user.UserRepositoryImpl;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.dto.user.UserResponse;

public class UserServiceImpl implements UserService {

    /**
     * invoking a repository instance
     */
    private UserRepository userRepository = UserRepositoryImpl.getInstance();

    /**
     * making it Singleton
     */
    private UserServiceImpl() {}
    private static final UserServiceImpl instance = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return instance;
    }

    /**
     * processing business logic
     */
    @Override
    public UserResponse signUp(UserRequest userRequest) {
        User user = userRepository.save(userRequest.toUser());
        return new UserResponse(user);
    }

    @Override
    public UserResponse signIn(UserRequest userRequest) {
        //세션에 저장
        return null;
    }
}
