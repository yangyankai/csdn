package com.nbgc.csdn.user;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yangyankai on 2016/3/11.
 */
public class UserUtil {
    public static boolean isLogin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinformation", context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if (username == "" || password == ""||"".equals(username)||"".equals(password) ) {
            return false;
        } else {
            return true;
        }
    }

    public static void setUser(Context context,String username,String password) {
        SharedPreferences mySharedPreferences= context.getSharedPreferences("userinformation", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }

    public static void exit(Context context) {
        SharedPreferences mySharedPreferences= context.getSharedPreferences("userinformation", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("username","");
        editor.putString("password","");
        editor.commit();
    }

    public static String  getName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinformation", context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        if (username == "" || password == ""||"".equals(username)||"".equals(password) ) {
            return "无登陆";
        } else {
            return ""+username;
        }
    }

}
