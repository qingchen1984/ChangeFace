package com.changeface.swb.changeface.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-11
 * Time: 22:20
 */
public class BaseActivity extends ActionBarActivity{
    public Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
    }
}
