package com.nbgc.csdn.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nbgc.csdn.BaseActivity;
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
 * 登录界面
 */

public class LoginActivity extends BaseActivity {

    private Button btnLogin;//登录
    private Button btnRegin;//注册
    private EditText editUsername;//用户名
    private EditText editPassword;//密码
    private static String uriAPI;
    private ProgressDialog dialog;
    private String strResult;//返回结果
    private static Handler handler = new Handler();
    private LoginActivity _this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _this = this;

        initView();
        setClick();
    }


    private void initView() {
        editPassword = (EditText) findViewById(R.id.password);
        editUsername = (EditText) findViewById(R.id.username);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegin = (Button) findViewById(R.id.btn_regin);
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在登录...");
        dialog.setCancelable(true);
    }

    private void setClick() {
        btnRegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new HttpThread()).start();
                dialog.show();
            }
        });
    }

    public class HttpThread implements Runnable {
        @Override
        public void run() {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.cancel();
                        if (UserUtil.login(_this, editUsername.getText().toString(), editPassword.getText().toString())) {
                            ToastUtil.toast(_this, "登录成功!");
                        } else {
                            ToastUtil.toast(_this, "登陆失败");
                        }
                    }
                });


//                uriAPI = "http://114.215.101.143:8080/NBTUNews/FindUser";
//                HttpPost httpRequest = new HttpPost(uriAPI);
//                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("username", "" + editUsername.getText()));
//                params.add(new BasicNameValuePair("password", "" + editPassword.getText()));
//                httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
//
//                if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                    strResult = EntityUtils.toString(httpResponse.getEntity());
//                }
//
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        dialog.cancel();
//                        ToastUtil.toast(getApplicationContext(), "" + strResult);
//                        if ("success".equals(strResult)) {
//                            finish();//调到主界面
//                            UserUtil.setUser(getApplicationContext(), "" + editUsername.getText(), "" + editPassword.getText());
//                        } else {
//                            //登录失败重新登陆
//                        }
//                    }
//                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
