package com.moneelab.assignment.service.feed;

import com.moneelab.assignment.dto.feed.ContentResponse;

import java.util.List;

public interface FeedService {
    List<ContentResponse> getContents();
}
