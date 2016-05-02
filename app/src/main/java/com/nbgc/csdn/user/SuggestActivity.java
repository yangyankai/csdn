package com.nbgc.csdn.user;

import android.os.Bundle;
import android.view.View;

import com.nbgc.csdnnews.R;

import cn.sharesdk.share.demo.BaseActivity;

/**
 * Created by ykai on 2016/5/1.
 */
public class SuggestActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
    }

    public void back(View v) {
        finish();
    }
}
