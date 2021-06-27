package com.moneelab.assignment.service.user;

import com.moneelab.assignment.domain.user.UserRepository;
import com.moneelab.assignment.dto.user.UserRequest;
import com.moneelab.assignment.dto.user.UserResponse;
import com.moneelab.assignment.exception.NotUniqueException;
import com.moneelab.assignment.exception.WrongLoginInputException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.moneelab.assignment.config.AppConfig.userRepository;
import static com.moneelab.assignment.config.AppConfig.userService;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService = userService();
    private UserRepository userRepository = userRepository();

    @AfterEach()
    void cleanup() {
        userRepository.clearAll();
    }

    @Test
    @DisplayName("UserService: 회원가입 정상 요청을 한다")
    void testSignUp_success() {
        //given
        UserRequest userRequest = new UserRequest();
        userRequest.setName("sample sign up username1");
        userRequest.setPassword("sample sign up password1");

        //when
        Long userId = userService.signUp(userRequest);

        //then
        assertNotNull(userId);
    }

    @Test
    @DisplayName("UserService: 이미 존재하는 이름으로 회원가입 요청한다")
    void testSignUp_fail_duplicatedName() {
        //given
        UserRequest userRequest1 = new UserRequest();
        userRequest1.setName("sample duplicated username");
        userRequest1.setPassword("sample duplicated password");

        //when
        userService.signUp(userRequest1);

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setName("sample duplicated username");
        userRequest2.setPassword("sample duplicated password");

        //then
        assertThrows(NotUniqueException.class,
                () -> userService.signUp(userRequest2),
                "사용자 이름은 중복되어선 안된다"
        );
    }

    @Test
    @DisplayName("UserService: 로그인 정상 요청한다")
    void testSignIn_success() {
        //given
        UserRequest userRequest = new UserRequest();
        userRequest.setName("sample sign in username1");
        userRequest.setPassword("sample sign in password1");

        userService.signUp(userRequest);

        //when
        UserResponse userResponse = userService.signIn(userRequest);

        //then
        assertNotNull(userResponse);
        assertNotNull(userResponse.getUserId());
        assertEquals(userRequest.getName(), userResponse.getUsername());
    }

    @Test
    @DisplayName("UserService: 존재하지 않는 이름으로 가입을 요청한다")
    void testSignIn_fail_noneExistentName() {
        //given
        UserRequest userRequest = new UserRequest();
        userRequest.setName("sample none-existent username");
        userRequest.setPassword("sample none-existent password");

        //when, then
        assertThrows(WrongLoginInputException.class,
                () -> userService.signIn(userRequest),
                "가입한 사용자만 로그인이 할 수 있다"
        );
    }

    @Test
    @DisplayName("UserService: 일치하지 않는 비밀번호로 요청한다")
    void testSignIn_fail_wrongPassword() {
        //given
        UserRequest userRequest = new UserRequest();
        userRequest.setName("sample wrongPassword username");
        userRequest.setPassword("sample wrongPassword password");

        userService.signUp(userRequest);

        userRequest.setPassword("sample wrongPassword password - wrong");

        //when, then
        assertThrows(WrongLoginInputException.class,
                () -> userService.signIn(userRequest),
                "비밀번호가 일치해야 한다."
        );
    }

    @Test
    @DisplayName("UserService: 모든 사용자이름을 가져온다")
    void testGetAllUsernames() {
        //given
        UserRequest userRequest = new UserRequest();
        for (int i=1; i<=25; i++) {
            userRequest.setName("sample getAllUsernames username"+i);
            userRequest.setPassword("sample getAllUsernames password"+i);

            userService.signUp(userRequest);
        }

        //when
        List<String> allUsernames = userService.getAllUsernames();

        //then
        assertEquals(25, allUsernames.size());
    }
}