package com.nbgc.csdn.user;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nbgc.csdn.BaseActivity;
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

public class ReginActivity extends BaseActivity {

    private Button btnDown;

    private TextView textViewUsername;
    private TextView textViewPassword;
    private TextView textViewEmail;

    private static String uriAPI;

    private ProgressDialog dialog;
    private String strResult;
    // 一个静态的Handler，Handler建议声明为静态的
    private static Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regin);
        btnDown = (Button) findViewById(R.id.btnDown);

        textViewUsername = (TextView) findViewById(R.id.username);
        textViewPassword = (TextView) findViewById(R.id.password);
        textViewEmail = (TextView) findViewById(R.id.email);

        dialog = new ProgressDialog(this);
        //  dialog.setTitle("提示");
        dialog.setMessage("正在登录...");
        dialog.setCancelable(true);

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开启一个子线程，用于下载 网页 xml
                new Thread(new MyThread()).start();
                // 显示对话框
                dialog.show();
            }
        });
    }

    public class MyThread implements Runnable {

        @Override
        public void run() {
            // 下载xml
            try {

                uriAPI = "http://114.215.101.143:8080/NBTUNews/AddUser";
                HttpPost httpRequest = new HttpPost(uriAPI);
                List<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("username", "" + textViewUsername.getText()));
                params.add(new BasicNameValuePair("password", "" + textViewPassword.getText()));
                params.add(new BasicNameValuePair("email", "" + textViewEmail.getText()));

                httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                HttpResponse httpResponse = new DefaultHttpClient()
                        .execute(httpRequest);

                if (httpResponse.getStatusLine().getStatusCode() == 200) {

                    strResult = EntityUtils.toString(httpResponse
                            .getEntity());
                    // textViewUsername.setText(strResult);
                } else {
                    // textViewUsername.setText("Error Response: "+httpResponse.getStatusLine().toString());
                }

                /*catch (ClientProtocolException e) {
                    textViewUsername.setText(e.getMessage().toString());
                    e.printStackTrace();
                } catch (IOException e) {
                    textViewUsername.setText(e.getMessage().toString());
                    e.printStackTrace();
                } catch (Exception e) {
                    textViewUsername.setText(e.getMessage().toString());
                    e.printStackTrace();
                }*/


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 在Post中操作UI组件mTextView
                        btnDown.setText("nihao");
                        textViewUsername.setText(strResult);
                        dialog.cancel();
                    }
                });
                // 隐藏对话框
                //         dialog.dismiss();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
