package com.prudhvi.httplib.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
    @Author : Prudhvi Kumar
*/
public class Messages {

    /* Network not found message */
    public static final String NET_WORK_MSG = "Please check your network try again!";
    public static final String CONN_EST = "Something went wrong!";

    /*
     * User bad request from API
     *
     * */
    public Map<Integer, String> errorCodes = new HashMap<>();

    /* error code from API */

    public static final String OK_400 = "Bad Request";
    public static final String OK_401 = "Unauthorized";
    public static final String OK_402 = "Payment Required";
    public static final String OK_403 = "Forbidden URL";
    public static final String OK_404 = "Not Found";
    public static final String OK_405 = "Method Not Allowed";
    public static final String OK_406 = "Not Acceptable";
    public static final String OK_407 = "Proxy Authentication Required";
    public static final String OK_408 = "Request Timeout";
    public static final String OK_409 = "Request could not be processed because of conflict in the current state of the resource";
    public static final String OK_411 = "Length Required";
    public static final String OK_413 = "Payload Too Large";
    public static final String OK_500 = "Internal Server Error";
    public static final String OK_502 = "Not Implemented";
    public static final String OK_503 = "Service Unavailable";
    public static final String OK_504 = "Gateway Timeout";
    public static final String OK_505 = "HTTP Version Not Supported";

    public Map<Integer, String> errorMessage() {
        errorCodes.put(400, OK_400);
        errorCodes.put(401, OK_401);
        errorCodes.put(402, OK_402);
        errorCodes.put(403, OK_403);
        errorCodes.put(404, OK_404);
        errorCodes.put(405, OK_405);
        errorCodes.put(407, OK_407);
        errorCodes.put(408, OK_408);
        errorCodes.put(409, OK_409);
        errorCodes.put(411, OK_411);
        errorCodes.put(413, OK_413);
        errorCodes.put(500, OK_500);
        errorCodes.put(502, OK_502);
        errorCodes.put(503, OK_503);
        errorCodes.put(504, OK_504);
        errorCodes.put(505, OK_505);

        return errorCodes;
    }
}
