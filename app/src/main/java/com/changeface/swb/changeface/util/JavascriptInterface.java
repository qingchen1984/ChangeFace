package com.changeface.swb.changeface.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.changeface.swb.changeface.activity.ChangeFaceActivity;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-14
 * Time: 21:40
 */
// js通信接口
public class JavascriptInterface {
    public static final String TAG = JavascriptInterface.class.getSimpleName();
    private Context context;

    public JavascriptInterface(Context context) {
        this.context = context;
    }

    public void openImage(String imgPath) {
        Log.i(TAG,imgPath);
        Intent intent = new Intent();
        intent.putExtra("image", imgPath);
        intent.setClass(context, ChangeFaceActivity.class);
        context.startActivity(intent);
    }
}
