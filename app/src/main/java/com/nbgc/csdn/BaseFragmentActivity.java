package com.nbgc.csdn;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by yangyankai on 2016/3/9.
 */
public class BaseFragmentActivity extends FragmentActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("yyk", "" + getLocalClassName());
    }
}
