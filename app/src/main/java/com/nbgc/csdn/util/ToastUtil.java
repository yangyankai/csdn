package com.nbgc.csdn.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil
{
	public static void toast(Context context , String msg)
	{
		Toast.makeText(context, msg, 1).show();
	}
}
