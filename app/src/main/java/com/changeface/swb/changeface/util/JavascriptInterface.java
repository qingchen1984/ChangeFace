package com.changeface.swb.changeface.util;

import android.content.Context;
import android.content.Intent;

import com.changeface.swb.changeface.activity.ChangeFaceActivity;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-14
 * Time: 21:40
 */
// js通信接口
public class JavascriptInterface {
    private Context context;

    public JavascriptInterface(Context context) {
        this.context = context;
    }

    public void openImage(String img) {
        System.out.println(img);
        //
        Intent intent = new Intent();
        intent.putExtra("image", img);
        intent.setClass(context, ChangeFaceActivity.class);
        context.startActivity(intent);
        System.out.println(img);
    }
}
