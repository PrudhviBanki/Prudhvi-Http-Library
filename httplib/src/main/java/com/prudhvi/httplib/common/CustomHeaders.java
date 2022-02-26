package com.prudhvi.httplib.common;

import java.util.HashMap;

public class CustomHeaders {
    String key;
    String Value;

    public HashMap<String,String> headers(){

        HashMap<String,String> myHeaders=new HashMap<>();

        return myHeaders;
    }

    public void setHeader(String key,String value){
        this.key=key;
        this.Value=value;
    }
}
