package com.prudhvi.httplib.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.prudhvi.httplib.callbacks.ResponseListener;
import com.prudhvi.httplib.common.CustomHeaders;
import com.prudhvi.httplib.common.Messages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private final Context context;

    private final ResponseListener callback;

    public int requestId = -1;

    private String contentType = "application/json";

    public Request(Context context, ResponseListener callback) {
        this.context = context;
        this.callback = callback;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void fetchObject(int requestId, int methodName, final String url, final JSONObject postData) {

        this.requestId = requestId;
        if (!isNetworkAvailable()) {
            new Handler(Looper.getMainLooper()).post(() -> callback.failure(Messages.NET_WORK_MSG, requestId));
            return;
        }

        new Thread(() -> {
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(methodName, url, postData,
                    response -> {
                        postUserAction(requestId, response);
                        Log.d("url", url);
                        Log.d("Response", response.toString());
                    }, this::parseVolleyError) {

                /** Passing some request headers* */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return new CustomHeaders().getHeaders();
                }

            };
            queue.add(jsonObjectRequest).setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 5000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 0; //retry turn off
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });
            queue.add(jsonObjectRequest);

        }).start();
    }

    public void fetchArray(int requestId, int methodName, final String url, final JSONArray postData) {

        this.requestId = requestId;
        if (!isNetworkAvailable()) {
            new Handler(Looper.getMainLooper()).post(() -> callback.failure(Messages.NET_WORK_MSG, requestId));
            return;
        }

        new Thread(() -> {
            RequestQueue queue = Volley.newRequestQueue(context);
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(methodName, url,postData, response -> {
                postUser(requestId, response);
                Log.d("url", url);
                Log.d("Response", response.toString());
            }, this::parseVolleyError) {

                /** Passing some request headers* */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    return new CustomHeaders().getHeaders();
                }

            };
            queue.add(jsonObjectRequest).setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 5000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 0; //retry turn off
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });
            queue.add(jsonObjectRequest);

        }).start();
    }

    private void parseVolleyError(VolleyError error) {
        try {
            NetworkResponse response = error.networkResponse;
            String responseBody = new String(response.data, StandardCharsets.UTF_8);
            JSONObject data = new JSONObject(responseBody);
            JSONArray errors = data.getJSONArray("errors");
            JSONObject jsonMessage = errors.getJSONObject(0);
            String message = jsonMessage.getString("message");
            new Handler(Looper.getMainLooper()).post(() -> callback.failure(message, requestId));

        } catch (JSONException ignored) {
        }

    }


    private void postUserAction(int requestId, final JSONObject response) {

        new Handler(Looper.getMainLooper()).post(() -> showUserActionResult(requestId, response));
    }

    private void postUser(int requestId, final JSONArray response) {

        new Handler(Looper.getMainLooper()).post(() -> showUserAction(requestId, response));
    }

    private void showUserActionResult(int requestId, JSONObject response) {
        callback.successObject(response, requestId);
    }

    private void showUserAction(int requestId, JSONArray response) {
        callback.successArray(response, requestId);
    }

    /**
     * checkConnectivity - Checks Whether Internet connection is available or
     * not.
     */
    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }

        NetworkInfo net = manager.getActiveNetworkInfo();
        if (net != null) {
            return net.isConnected();
        } else {
            return false;
        }
    }
}
