package com.nbgc.csdn.user;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yangyankai on 2016/3/11.
 */
public class UserUtil {
    public static boolean  isLogin(Context context) {
        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinformation",
                context.MODE_PRIVATE);
// 使用getString方法获得value，注意第2个参数是value的默认值
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
//使用toast信息提示框显示信息
        if (username == "" || password == "") {
            return false;
        } else {
            return true;
        }


    }
}
