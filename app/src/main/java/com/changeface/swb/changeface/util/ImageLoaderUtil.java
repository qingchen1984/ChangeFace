package com.changeface.swb.changeface.util;

import android.graphics.Bitmap;

import com.changeface.swb.changeface.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-14
 * Time: 23:09
 */
public class ImageLoaderUtil {
    public static DisplayImageOptions getImageOptions(int imageRes){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(imageRes) // resource or drawable
                .showImageForEmptyUri(imageRes) // resource or drawable
                .showImageOnFail(imageRes) // resource or drawable
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }
}
