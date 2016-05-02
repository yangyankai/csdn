package cn.sharesdk.share.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by yangyankai on 2016/3/9.
 */
public class BaseActivity extends Activity {
    public BaseActivity _this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _this = this;
        Log.e("yyk", "" + getPackageName());
    }
}
