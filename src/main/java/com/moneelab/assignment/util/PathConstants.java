package com.moneelab.assignment.util;

public abstract class PathConstants {
    //Common Path for All
    public static final String P_COMMON = "/api/v1";

    //User Path
    public static final String P_USER = P_COMMON + "/user";
    public static final String P_ALL_USERNAMES = P_USER + "/allUsernames";
    public static final String P_SIGN_UP = P_USER + "/signUp";
    public static final String P_SIGN_IN = P_USER + "/signIn";
    public static final String P_LOGOUT = P_USER + "/logout";

    //Feed Path
    public static final String P_FEED = P_COMMON + "/feed";

    //Post Path
    public static final String P_POST = P_COMMON + "/post";

    //Comment Path
    public static final String P_COMMENT = P_COMMON + "/comment";

    //Like Path
    public static final String P_LIKE = P_COMMON + "/like";
}
