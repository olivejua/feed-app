package com.moneelab.assignment.web.controller.like;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.like.LikeResponse;
import com.moneelab.assignment.service.like.LikeService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.moneelab.assignment.config.AppConfig.likeService;

public class LikeControllerImpl implements LikeController {

    /**
     * invoking a service instance
     */
    private LikeService likeService = likeService();

    /**
     * making it Singleton
     */
    private LikeControllerImpl() {}
    private static final LikeControllerImpl instance = new LikeControllerImpl();

    public static LikeControllerImpl getInstance() {
        return instance;
    }

    /**
     * processing presentation logic
     */
    //TODO 사용자인증 구현하면 세션으로 로그인사용자 주입하기
    @Override
    public ResponseEntity doLike(Map<String, String> paramMap, SessionUserService sessionService) {
        Long postId = Long.parseLong(paramMap.get("postId"));
        Long likeId = likeService.doLike(postId, sessionService.getUser().getUserId());

        return new ResponseEntity(HttpServletResponse.SC_CREATED, likeId);
    }

    //TODO 사용자인증 구현하면 세션으로 로그인사용자 주입하기
    @Override
    public ResponseEntity cancelLike(Map<String, String> paramMap, SessionUserService sessionService) {
        Long postId = Long.parseLong(paramMap.get("postId"));

        LikeResponse likeResponse = likeService.findOneByPostId(postId);
        validateUserWhoLiked(likeResponse.getUserId(), sessionService);

        likeService.cancelLike(postId, 0L);

        return new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
    }

    private void validateUserWhoLiked(Long userId, SessionUserService sessionService) {
        if (userId.equals(sessionService.getUser().getUserId())) {
            //TODO 예외
        }
    }
}
