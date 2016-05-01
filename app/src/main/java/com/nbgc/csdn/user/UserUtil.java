package com.nbgc.csdn.user;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yangyankai on 2016/3/11.
 */
public class UserUtil {
    //是否登陆
    public static boolean isLogin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinformation", context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if (username == "" || password == "" || "".equals(username) || "".equals(password)) {
            return false;
        } else {
            return true;
        }
    }

    //保存用户名、密码
    public static void setUser(Context context, String username, String password) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("userinformation", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }

    //退出登陆，清除密码
    public static void exit(Context context) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("userinformation", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("username", "");
        editor.putString("password", "");
        editor.commit();
    }

    //取出用户名
    public static String getName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinformation", context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        return "" + username;
    }

    //取出密码
    public static String getPassword(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinformation", context.MODE_PRIVATE);
        String password = sharedPreferences.getString("password", "");
        return "" + password;
    }

    // 判断用户名和密码
    //成功返回true，失败返回false
    public static boolean login(Context context, String name, String pass) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinformation", context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if (name.equals(username) && pass.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
