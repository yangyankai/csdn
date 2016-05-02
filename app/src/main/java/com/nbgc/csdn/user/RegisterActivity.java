package com.nbgc.csdn.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nbgc.csdn.util.ToastUtil;
import com.nbgc.csdnnews.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册
 */
public class RegisterActivity extends Activity {

    private Button btnRegin;//登录
    private EditText editUsername;//用户名
    private EditText editPassword;//密码
    private EditText editEmail;//电子邮件
    private RegisterActivity _this;

    private static String uriAPI;
    private ProgressDialog dialog;
    private String strResult;
    private static Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regin);
        _this = this;

        initView();
        setClick();
    }

    private void setClick() {
        btnRegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new HttpThread()).start();
                dialog.show();
            }
        });
    }

    private void initView() {
        btnRegin = (Button) findViewById(R.id.btn_regin);
        editUsername = (EditText) findViewById(R.id.username);
        editPassword = (EditText) findViewById(R.id.password);
        editEmail = (EditText) findViewById(R.id.email);
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在注册...");
        dialog.setCancelable(true);
    }

    public class HttpThread implements Runnable {

        @Override
        public void run() {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        UserUtil.setUser(_this, editUsername.getText().toString(), editPassword.getText().toString());
                        dialog.cancel();
                        ToastUtil.toast(_this, "注册成功!");
                    }
                });

//                uriAPI = "http://114.215.101.143:8080/NBTUNews/AddUser";
//                HttpPost httpRequest = new HttpPost(uriAPI);
//                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("username", "" + editUsername.getText()));
//                params.add(new BasicNameValuePair("password", "" + editPassword.getText()));
//                params.add(new BasicNameValuePair("email", "" + editEmail.getText()));
//                httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
//
//                if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                    strResult = EntityUtils.toString(httpResponse.getEntity());
//                } else {
//                    return;
//                }
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        dialog.cancel();
//                        ToastUtil.toast(getApplicationContext(), "" + strResult);
//                        if("success".equals(strResult)){
//                            finish();//调到登录界面
//                        }else{
//
//                        }
//                    }
//                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void back(View v) {
        finish();
    }
}
