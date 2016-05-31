package com.nbgc.csdn.http;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class PersonLoader {

    private MyData data;  //网络底层
    private Context context;

    //    构造函数，初始化
    public PersonLoader(Context context) {

        data = new MyData(context);
        this.context = context;
    }

    /**
     * @param getListener     成功回掉参数
     * @param sfErrorListener 回掉失败参数
     */

    public void getPerson(final GetListener getListener, final SfErrorListener sfErrorListener) {

        data.getData("http://114.55.1.119:8080/member/me/profile",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        getListener.getSuccess(response);//成功，且返回正确数据

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        sfErrorListener.GetErrorMsg(error.getMessage());//失败
                    }
                });
    }

    public interface GetListener {
        void getSuccess(String strPerson);
    }

    public interface SfErrorListener {

        void GetErrorMsg(String msg);

    }

}
