package com.changeface.swb.changeface.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.changeface.swb.changeface.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.SinaShareContent;
import com.umeng.socialize.media.SmsShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.EmailHandler;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-18
 * Time: 12:33
 */
public class ShareUtil {
    private final static UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");
    public static UMSocialService getUMSocialService(){
        return mController;
    }
    public static void shareToQQ(Activity activity){
        // 添加QQ平台
        UMQQSsoHandler qqHandler = new UMQQSsoHandler(activity,
                "100424468", "c7394704798a158208a74ab60104f0ba");
        qqHandler.addToSocialSDK();
        mController.setShareMedia(setQQShareContent(activity));
        share(activity,SHARE_MEDIA.QQ);
    }
    public static void shareToQQZone(Activity activity){
        // 添加QQ空间平台
        QZoneSsoHandler qzoneHandler = new QZoneSsoHandler(activity,
                "100424468", "c7394704798a158208a74ab60104f0ba");
        qzoneHandler.addToSocialSDK();
        mController.setShareMedia(setQZoneShareContent(activity));
        share(activity, SHARE_MEDIA.QZONE);
    }
    public static void shareToSina(Context context) {
        SinaSsoHandler sinaSsoHandler = new SinaSsoHandler();
        sinaSsoHandler.addToSocialSDK();
        mController.setShareMedia(setSinaShareContent(context));
        share(context,SHARE_MEDIA.SINA);
    }
    public static void shareToSMS(Context context) {
        SmsHandler smsHandler = new SmsHandler();
        smsHandler.addToSocialSDK();
        mController.setShareMedia(setSmsShareContent(context));
        share(context,SHARE_MEDIA.SMS);
    }

    public static void shareToEmail(Context context) {
        EmailHandler emailHandler = new EmailHandler();
        emailHandler.addToSocialSDK();
        share(context, SHARE_MEDIA.EMAIL);
    }
    public static QQShareContent setQQShareContent(Context context){
        QQShareContent qqShareContent = new QQShareContent();
//设置分享文字
        qqShareContent.setShareContent(context.getString(R.string.share_content));
//设置分享title
        qqShareContent.setTitle(context.getResources().getString(R.string.app_name));
//设置分享图片
        qqShareContent.setShareImage(new UMImage(context, R.drawable.ic_launcher));
//设置点击分享内容的跳转链接
        qqShareContent.setTargetUrl(context.getString(R.string.share_url));
        return qqShareContent;
    }
    public static QZoneShareContent setQZoneShareContent(Context context){
        QZoneShareContent qzoneContent = new QZoneShareContent();
//设置分享文字
        qzoneContent.setShareContent(context.getString(R.string.share_content));
//设置点击消息的跳转URL
        qzoneContent.setTargetUrl(context.getString(R.string.share_url));
//设置分享内容的标题
        qzoneContent.setTitle(context.getResources().getString(R.string.app_name));
//设置分享图片
        qzoneContent.setShareImage(new UMImage(context, R.drawable.ic_launcher));
        return qzoneContent;
    }

    public static SinaShareContent setSinaShareContent(Context context){
        SinaShareContent sinaShareContent = new SinaShareContent();
        sinaShareContent.setTitle(context.getResources().getString(R.string.app_name));
        sinaShareContent.setShareContent(context.getString(R.string.share_content)+context.getString(R.string.share_url));
        sinaShareContent.setTargetUrl(context.getString(R.string.share_url));
        return sinaShareContent;
    }
    public static SmsShareContent setSmsShareContent(Context context){
        SmsShareContent smsShareContent = new SmsShareContent();
        smsShareContent.setShareContent(context.getString(R.string.share_content)+context.getString(R.string.share_url));
        return smsShareContent;
    }
    private static void share(final Context context,SHARE_MEDIA platform){
//        // 设置文字分享内容
//        mController.setShareContent("这是文字分享内容");
//        // 图片分享内容
//        mController.setShareMedia(new UMImage(context,
//                R.drawable.ic_launcher));
        // 参数1为Context类型对象， 参数2为要分享到的目标平台， 参数3为分享操作的回调接口
        mController.postShare(context, platform,
                new SocializeListeners.SnsPostListener() {
                    @Override
                    public void onStart() {
                      //  Toast.makeText(context, "开始分享", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onComplete(SHARE_MEDIA platform, int eCode,SocializeEntity entity) {
                        if (eCode == 200) {
                            Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
                        } else {
                            String eMsg = "";
                            if (eCode == -101){
                                eMsg = "没有授权";
                            }
                            Toast.makeText(context, "分享失败[" + eCode + "] " +
                                    eMsg,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
