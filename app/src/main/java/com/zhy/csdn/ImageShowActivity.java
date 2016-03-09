package com.zhy.csdn;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.polites.android.GestureImageView;
import com.zhy.csdn.util.FileUtil;
import com.zhy.csdn.util.Http;
import com.zhy.csdnnews.R;

public class ImageShowActivity extends BaseActivity
{

	private String url;
	private ProgressBar mLoading;
	private GestureImageView mGestureImageView;
	private Bitmap mBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_page);


		url = getIntent().getExtras().getString("url");
		mLoading = (ProgressBar) findViewById(R.id.loading);
		mGestureImageView = (GestureImageView) findViewById(R.id.image);

		new DownloadImgTask().execute();

	}

	/**
	 *
	 * 
	 * @param view
	 */
	public void back(View view)
	{
		finish();
	}

	/**
	 *
	 * 
	 * @param view
	 */
	public void downloadImg(View view)
	{
		mGestureImageView.setDrawingCacheEnabled(true);
		if (FileUtil.writeSDcard(url, mGestureImageView.getDrawingCache()))
		{
			Toast.makeText(getApplicationContext(), "下载成功", Toast.LENGTH_SHORT).show();
		} else
		{
			Toast.makeText(getApplicationContext(), "下载失败", Toast.LENGTH_SHORT).show();
		}
		mGestureImageView.setDrawingCacheEnabled(false);
	}

	class DownloadImgTask extends AsyncTask<Void, Void, Void>
	{
		@Override
		protected Void doInBackground(Void... params)
		{
			mBitmap = Http.HttpGetBmp(url);
			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			mGestureImageView.setImageBitmap(mBitmap);
			mLoading.setVisibility(View.GONE);
			super.onPostExecute(result);
		}

	}
}
