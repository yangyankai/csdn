package com.nbgc.csdn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.nbgc.csdn.user.LoginActivity;
import com.nbgc.csdn.user.UserUtil;
import com.viewpagerindicator.TabPageIndicator;

import cn.sharesdk.framework.ShareSDK;

public class MainActivity extends BaseFragmentActivity
{
	private TabPageIndicator mIndicator ;
	private ViewPager mViewPager ;
	private FragmentPagerAdapter mAdapter ;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if(!UserUtil.isLogin(this))
		{
			Intent intent=new Intent(this, LoginActivity.class);
			startActivity(intent);
			//去登录
		}
		setContentView(com.nbgc.csdnnews.R.layout.activity_main);
		mIndicator = (TabPageIndicator) findViewById(com.nbgc.csdnnews.R.id.id_indicator);
		mViewPager = (ViewPager) findViewById(com.nbgc.csdnnews.R.id.id_pager);
		mAdapter = new TabAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mIndicator.setViewPager(mViewPager, 0);

		// 初始化ShareSDK
		ShareSDK.initSDK(this);
		
	}



}
