package com.nbgc.csdn;

import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;
import cn.sharesdk.share.demo.*;
import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.XListView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;

import com.nbgc.csdn.util.ToastUtil;
import com.zhy.bean.CommonException;
import com.zhy.bean.News;
import com.zhy.biz.NewsItemBiz;
import com.nbgc.csdn.adapter.NewContentAdapter;
import com.nbgc.csdnnews.R;


public class NewsContentActivity extends BaseActivity implements IXListViewLoadMore
{

	private XListView mListView;

	/**
	 *
	 */
	private String url;
	private NewsItemBiz mNewsItemBiz;
	private List<News> mDatas;
	private ImageView imageViewShare;

	private ProgressBar mProgressBar;
	private NewContentAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_content);

		mNewsItemBiz = new NewsItemBiz();
		imageViewShare= (ImageView) findViewById(R.id.comment);
		imageViewShare.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				ToastUtil.toast(getApplicationContext(),"分享成功");
				showShare(getApplicationContext(), null, true,url);
//				startActivity(new Intent(getApplicationContext(), cn.sharesdk.share.demo.MainActivity.class));
			}
		});

		Bundle extras = getIntent().getExtras();
		url = extras.getString("url");

		mAdapter = new NewContentAdapter(this);
		
		mListView = (XListView) findViewById(R.id.id_listview);
		mProgressBar = (ProgressBar) findViewById(R.id.id_newsContentPro);

		mListView.setAdapter(mAdapter);
		mListView.disablePullRefreash();
		mListView.setPullLoadEnable(this);
		
		mListView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				
				News news = mDatas.get(position - 1);
				String imageLink = news.getImageLink();
				Log.e("yyk","url: "+imageLink);
				//Toast.makeText(NewContentActivity.this, imageLink, 1).show();
				Intent intent = new Intent(NewsContentActivity.this,ImageShowActivity.class);
				intent.putExtra("url", imageLink);
				startActivity(intent);
			}
		});
		
		mProgressBar.setVisibility(View.VISIBLE);
		new LoadDataTask().execute();

	}

	@Override
	public void onLoadMore()
	{

	}

	class LoadDataTask extends AsyncTask<Void, Void, Void>
	{

		
		
		@Override
		protected Void doInBackground(Void... params)
		{
			try
			{
				mDatas = mNewsItemBiz.getNews(url).getNewses();
				Log.e("yyk","内容 url: "+url);
			} catch (CommonException e)
			{
				Looper.prepare();
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				Looper.loop();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			if(mDatas == null)
				return ; 
			mAdapter.addList(mDatas);
			mAdapter.notifyDataSetChanged();
			mProgressBar.setVisibility(View.GONE);
		}

	}
	
	/**
	 *
	 * @param view
	 */
	public void back(View view)
	{
		finish();
	}

	/**
	 * 演示调用ShareSDK执行分享
	 *
	 * @param context
	 * @param platformToShare  指定直接分享平台名称（一旦设置了平台名称，则九宫格将不会显示）
	 * @param showContentEdit  是否显示编辑页
	 */
	public static void showShare(Context context, String platformToShare, boolean showContentEdit,String strShare) {
		OnekeyShare oks = new OnekeyShare();
		oks.setSilent(!showContentEdit);
		if (platformToShare != null) {
			oks.setPlatform(platformToShare);
		}
		//ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC  第二个是SKYBLUE
		oks.setTheme(OnekeyShareTheme.CLASSIC);
		// 令编辑页面显示为Dialog模式
		oks.setDialogMode();
		// 在自动授权时可以禁用SSO方式
		oks.disableSSOWhenAuthorize();
		//oks.setAddress("12345678901"); //分享短信的号码和邮件的地址
		oks.setTitle("");//ShareSDK--Title");
		oks.setTitleUrl(strShare);
		oks.setText(""+strShare);
		//oks.setImagePath("/sdcard/test-pic.jpg");  //分享sdcard目录下的图片
		oks.setImageUrl("");//randomPic()[0]);
		oks.setUrl(strShare);//http://www.mob.com"); //微信不绕过审核分享链接
		//oks.setFilePath("/sdcard/test-pic.jpg");  //filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提供
		oks.setComment("分享"); //我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
		oks.setSite("新闻");  //QZone分享完之后返回应用时提示框上显示的名称
		oks.setSiteUrl(strShare);//http://mob.com");//QZone分享参数
		oks.setVenueName("ShareSDK");
		oks.setVenueDescription("This is a beautiful place!");
		// 将快捷分享的操作结果将通过OneKeyShareCallback回调
		//oks.setCallback(new OneKeyShareCallback());
		// 去自定义不同平台的字段内容
		//oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());
		// 在九宫格设置自定义的图标
		Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		String label = "ShareSDK";
		View.OnClickListener listener = new View.OnClickListener() {
			public void onClick(View v) {

			}
		};
		oks.setCustomerLogo(logo, label, listener);

		// 启动分享
		oks.show(context);
	}
}
