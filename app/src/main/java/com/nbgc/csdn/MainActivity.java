package com.nbgc.csdn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbgc.csdn.user.LoginActivity;
import com.nbgc.csdn.user.UserUtil;
import com.nbgc.csdn.util.ToastUtil;
import com.nbgc.csdnnews.R;
import com.viewpagerindicator.TabPageIndicator;

import cn.sharesdk.framework.ShareSDK;
//主界面，消息界面
public class MainActivity extends BaseFragmentActivity {
    private TabPageIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private ImageView imageExit;
    private TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.nbgc.csdnnews.R.layout.activity_main);
        imageExit = (ImageView) findViewById(R.id.image_exit);
        tvUsername= (TextView) findViewById(R.id.tv_username);
        mIndicator = (TabPageIndicator) findViewById(com.nbgc.csdnnews.R.id.id_indicator);
        mViewPager = (ViewPager) findViewById(com.nbgc.csdnnews.R.id.id_pager);
        checkIsLogin();
        setUsername();
        mAdapter = new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);

        imageExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.exit(getApplicationContext());
                ToastUtil.toast(getApplicationContext(), "已经退出");
                checkIsLogin();
                setUsername();
            }
        });

        // 初始化ShareSDK
        ShareSDK.initSDK(this);

    }

    private void checkIsLogin() {
        if (!UserUtil.isLogin(this)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            //去登录
        }
    }

    private void setUsername() {
        tvUsername.setText(""+UserUtil.getName(getApplicationContext()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUsername();
    }
}
