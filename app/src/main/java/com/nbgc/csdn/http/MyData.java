package com.nbgc.csdn.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyData {
    Context context;


    public MyData(Context c) {
        context = c;


    }


    // post请求
    public void postData(final HashMap<String, String> map, final String url, final Listener<String> listener, final ErrorListener errorListener) {


        final HashMap<String, String> params = map;
        StringRequest sr = new StringRequest(Method.POST, url,
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {   // 200的会在这里拦截
                        httpLog(url, map, response);
                        if (listener != null) {
                            listener.onResponse(response);
                        }
                    }
                }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {//   400等，不是200的要在这里拦截

                if (errorListener != null) {
                    errorListener.onErrorResponse(error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                return headers;
            }
        };
        sr.setShouldCache(false);
        sr.setTag(url);

    }


    // post请求,参数里面是body,body里面是对象转化成的Json
    public void postDataByBody(final String json, final String url, final Listener<String> listener, final ErrorListener errorListener) {

        StringRequest sr = new StringRequest(Method.POST, url,
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {    // 200的会在这里拦截
                        httpLog(url, json, response);

                        if (listener != null) {
                            listener.onResponse(response);
                        }
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {//   400等，不是200的Volley在这里拦截

                if (errorListener != null) {
                    errorListener.onErrorResponse(error);
                }
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                return json.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put("content-type", "application/json");
                return headers;
            }
        };
        sr.setShouldCache(false);
        sr.setTag(url);

    }

    // get请求,参数只能拼接到Url中
    public void getData(final String url, final Listener<String> listener, final ErrorListener errorListener) {

        StringRequest sr = new StringRequest(Method.GET, url,
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {// 200的会在这里拦截
                        httpLog(url, response);
                        if (listener != null) {
                            listener.onResponse(response);
                        }
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {//   400等，不是200的要在这里拦截

                if (errorListener != null) {
                    errorListener.onErrorResponse(error);
                }
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put("", "");
                return headers;
            }
        };
        sr.setShouldCache(false);
        sr.setTag(url);

    }


    // post请求
    public void postData(String mapStr, String url, Listener<String> listener,
                         ErrorListener errorListener) {

        final Map<String, String> params = new HashMap<String, String>();
        params.put("params", mapStr);
        StringRequest sr = new StringRequest(Method.POST, url, listener,
                errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        sr.setShouldCache(false);
        sr.setTag(url);

    }


    private void httpLog(String url, HashMap<String, String> map, String response) {
        Log.w("HttpPostLog: ", " url " + url + "  reponse " + response);
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            Log.w("HttpPostLog: ", "key: " + key.toString() + "  value: " + val);
        }
    }


    private void httpLog(String url, String response) {
        Log.w("HttpGetLog: ", " url " + url + "  response " + response);
    }

    private void httpLog(String url, String json, String response) {
        Log.w("HttpPostLog: ", " url " + url + "  jons: " + json + "  response " + response);
    }

}
