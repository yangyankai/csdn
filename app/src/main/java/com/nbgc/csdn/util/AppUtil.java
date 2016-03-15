package com.nbgc.csdn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.text.TextUtils;

/**
 * 
 * @author
 * 
 */
public class AppUtil
{
	/**
	 * 
	 * @param newType
	 * @return
	 */
	public static String getRefreashTime(Context context, int newType)
	{
		String timeStr = PreferenceUtil.readString(context, "NEWS_" + newType);
		if (TextUtils.isEmpty(timeStr))
		{
			return "已经更新.";
		}
		return timeStr;
	}
	
	
	/**
	 * @param newType
	 * @return
	 */
	public static void setRefreashTime(Context context, int newType)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PreferenceUtil.write(context, "NEWS_" + newType,df.format(new Date()));
	}
}
