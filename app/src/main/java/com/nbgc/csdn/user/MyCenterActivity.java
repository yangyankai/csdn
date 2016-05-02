package com.nbgc.csdn.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nbgc.csdn.util.ToastUtil;
import com.nbgc.csdnnews.R;

import cn.sharesdk.share.demo.BaseActivity;

/**
 * Created by ykai on 2016/5/1.
 */
public class MyCenterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        findViewById(R.id.btn_suggest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_this, SuggestActivity.class);
                startActivity(intent);

            }
        });
        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserUtil.exit(getApplicationContext());
                ToastUtil.toast(getApplicationContext(), "已经退出");
//                checkIsLogin();
//                setUsername();
            }
        });

    }

    public void back(View v) {
        finish();
    }
}
