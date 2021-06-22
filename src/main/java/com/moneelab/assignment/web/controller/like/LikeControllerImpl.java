package com.moneelab.assignment.web.controller.like;

public class LikeControllerImpl implements LikeController {
    @Override
    public String doLike() {
        return "result doLike";
    }

    @Override
    public String cancelLike() {
        return "result cancelLike";
    }
}
