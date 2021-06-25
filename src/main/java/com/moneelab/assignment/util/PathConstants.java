package com.moneelab.assignment.util;

public abstract class PathConstants {
    //Common Path for All
    public static final String COMMON_ALL = "/api/v1";

    //User Path
    public static final String USER = COMMON_ALL + "/user";
    public static final String USER_SIGN_UP = USER + "/signup";
    public static final String USER_SIGN_IN = USER + "/signin";
    public static final String USER_LOGOUT = USER + "/logout";

    //Feed Path
    public static final String FEED = COMMON_ALL  + "/feed";

    //Post Path
    public static final String COMMON_POST = COMMON_ALL + "/post";

    //Comment Path
    public static final String COMMON_COMMENT = COMMON_ALL + "/comment";

    public static final String COMMON_LIKE = COMMON_ALL + "/like";

}
