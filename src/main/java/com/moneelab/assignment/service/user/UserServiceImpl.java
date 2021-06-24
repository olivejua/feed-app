package com.moneelab.assignment.service.user;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.domain.user.User;
import com.moneelab.assignment.domain.user.UserRepository;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.dto.user.UserResponse;

import javax.servlet.http.HttpSession;

import static com.moneelab.assignment.config.AppConfig.userRepository;

public class UserServiceImpl implements UserService {

    /**
     * invoking a repository instance
     */
    private UserRepository userRepository = userRepository();

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
    public synchronized UserResponse signUp(UserRequest userRequest) {
        User user = userRepository.save(userRequest.toUser());
        return new UserResponse(user);
    }

    @Override
    public synchronized UserResponse signIn(UserRequest userRequest) {
        //세션에 저장
        User user = userRepository.findByName(userRequest.getName())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이름입니다."));

        if (!userRequest.getPassword().equals(user.getPassword())) {
            // 비밀번호가 일치하지 않습니다.
        }

        return new UserResponse(user);
    }
}
