package com.prudhvi.httplib.callbacks;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ResponseListener {

    void successObject(JSONObject obj, int requestId);

    void successArray(JSONArray obj, int requestId);

    void failure(String response, int requestId);
}
