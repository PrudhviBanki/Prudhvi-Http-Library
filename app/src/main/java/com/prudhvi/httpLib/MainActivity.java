package com.prudhvi.httpLib;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prudhvi.httplib.callbacks.Methods;
import com.prudhvi.httplib.callbacks.ResponseListener;
import com.prudhvi.httplib.network.Request;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ResponseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Request request = new Request(this, this);
        request.fetchArray(100, Methods.GET, "http://universities.hipolabs.com/search?country=United+States", null);

    }

    @Override
    public void successObject(JSONObject obj, int requestId) {

    }

    @Override
    public void successArray(JSONArray obj, int requestId) {

    }

    @Override
    public void failure(String response, int requestId) {

    }
}