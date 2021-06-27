package com.moneelab.assignment.service.post;

import com.moneelab.assignment.dto.post.PostRequest;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.moneelab.assignment.config.AppConfig.postService;

public class PostServiceTest {

    private PostService postService = postService();

    @Test
    void testSave() {
        //given
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("sample title1234");
        postRequest.setContent("sample content1234");

//        Mockito.mockitoSession()



        //when

        //then

    }
}
