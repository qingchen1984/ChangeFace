package com.changeface.swb.changeface.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-11
 * Time: 23:21
 */
public class ChangeFaceApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
    }
}
