package com.moneelab.assignment.web.controller.like;

import com.moneelab.assignment.dto.ResponseEntity;
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
    public ResponseEntity doLike(Map<String, String> paramMap) {
        Long postId = Long.parseLong(paramMap.get("postId"));
        Long likeId = likeService.doLike(postId, 0L);

        return new ResponseEntity(HttpServletResponse.SC_CREATED, likeId);
    }

    //TODO 사용자인증 구현하면 세션으로 로그인사용자 주입하기
    @Override
    public ResponseEntity cancelLike(Map<String, String> paramMap) {
        Long postId = Long.parseLong(paramMap.get("postId"));
        likeService.cancelLike(postId, 0L);

        return new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
    }
}
