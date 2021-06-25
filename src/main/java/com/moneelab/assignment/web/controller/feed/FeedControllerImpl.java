package com.moneelab.assignment.web.controller.feed;

import com.moneelab.assignment.dto.ResponseEntity;
import com.moneelab.assignment.dto.feed.ContentResponse;
import com.moneelab.assignment.service.feed.FeedService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.moneelab.assignment.config.AppConfig.feedService;

public class FeedControllerImpl implements FeedController {
    /**
     * invoking a service instance
     */
    private FeedService feedService = feedService();

    /**
     * making it Singleton
     */
    private FeedControllerImpl() {}
    private static final FeedControllerImpl instance = new FeedControllerImpl();

    public static FeedControllerImpl getInstance() {
        return instance;
    }

    /**
     * processing presentation logic
     */
    @Override
    public ResponseEntity getContents() {
        List<ContentResponse> contents = feedService.getContents();

        return new ResponseEntity(HttpServletResponse.SC_OK, contents);
    }

}
