package com.moneelab.assignment.web.controller.like;

import com.moneelab.assignment.config.session.SessionUserService;
import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.web.controller.Controller;

import java.util.Map;

public interface LikeController extends Controller {
    ResponseEntity doLike(Map<String, String> paramMap, SessionUserService sessionService);
    ResponseEntity cancelLike(Map<String, String> paramMap, SessionUserService sessionService);
}
