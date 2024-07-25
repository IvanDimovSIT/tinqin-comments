package com.tinqinacademy.api;

public class RestApiRoutes {
    public static final String API = "/api/v1";
    public static final String HOTEL_BASE =  API + "/hotel";
    public static final String SYSTEM_BASE = API + "/system";

    public static final String HOTEL_GET_COMMENTS = HOTEL_BASE + "/{roomId}/comment";
    public static final String HOTEL_ADD_COMMENT = HOTEL_BASE + "/{roomId}/comment";
    public static final String HOTEL_EDIT_COMMENT = HOTEL_BASE + "/comment/{commentId}";

    public static final String SYSTEM_ADMIN_EDIT_COMMENT = SYSTEM_BASE + "/comment/{commentId}";
    public static final String SYSTEM_ADMIN_DELETE_COMMENT = SYSTEM_BASE + "/comment/{commentId}";
}
