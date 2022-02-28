package com.prudhvi.httplib.common;

import java.util.HashMap;

public class CustomHeaders {

    HashMap<String, String> headers = new HashMap<>();

    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    public HashMap<String,String> getHeaders() {
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
