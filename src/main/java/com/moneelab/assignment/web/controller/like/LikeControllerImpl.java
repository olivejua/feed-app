package com.moneelab.assignment.web.controller.like;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ErrorResponse;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.like.LikeResponse;
import com.moneelab.assignment.exception.NotAvailableException;
import com.moneelab.assignment.exception.NotExistException;
import com.moneelab.assignment.service.like.LikeService;
import com.moneelab.assignment.util.PathConstants;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.moneelab.assignment.config.AppConfig.likeService;
import static com.moneelab.assignment.util.PathConstants.*;

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
    @Override
    public ResponseEntity doLike(Map<String, String> paramMap, SessionUserService sessionService) {
        ResponseEntity result = null;

        try {
            Long postId = Long.parseLong(paramMap.get("postId"));
            Long likeId = likeService.doLike(postId, sessionService.getUser().getUserId());

            result = new ResponseEntity(HttpServletResponse.SC_CREATED, HOST+P_LIKE+"?id="+likeId);
        } catch (NotExistException | NotAvailableException e) {
            result = new ResponseEntity(HttpServletResponse.SC_BAD_REQUEST, new ErrorResponse(e.getMessage()));
        }

        return result;
    }

    @Override
    public ResponseEntity cancelLike(Map<String, String> paramMap, SessionUserService sessionService) {
        Long postId = Long.parseLong(paramMap.get("postId"));

        ResponseEntity result = null;
        try {
            Long userId = sessionService.getUser().getUserId();
            likeService.cancelLike(postId, userId);

            result = new ResponseEntity(HttpServletResponse.SC_NO_CONTENT);
        } catch (NotExistException | NotAvailableException e) {
            result = new ResponseEntity(HttpServletResponse.SC_BAD_REQUEST, new ErrorResponse(e.getMessage()));
        }

        return result;
    }

    @Override
    public ResponseEntity findLike(Map<String, String> paramMap) {
        Long likeId = Long.parseLong(paramMap.get("id"));

        ResponseEntity result = null;
        try {
            LikeResponse like = likeService.findOneById(likeId);

            result = new ResponseEntity(HttpServletResponse.SC_OK, like);
        } catch (NotExistException e) {

            result = new ResponseEntity(HttpServletResponse.SC_BAD_REQUEST, new ErrorResponse(e.getMessage()));
        }

        return result;
    }
}
