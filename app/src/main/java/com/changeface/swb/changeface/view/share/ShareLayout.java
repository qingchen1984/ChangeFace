package com.changeface.swb.changeface.view.share;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changeface.swb.changeface.R;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-18
 * Time: 12:13
 */
public class ShareLayout extends LinearLayout implements View.OnClickListener{
    private Context mContext;
    private TextView mTitle;
    private View mShareToQQ;
    private View mShareToQQZone;
    private View mShareToQQSina;
    private View mShareToSMS;
    private View mShareToEmail;
    private View mOther;
    private ShareListener mShareListener;
    public ShareLayout(Context context) {
        super(context);
        mContext = context;
    }

    public ShareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public ShareLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitle = (TextView) findViewById(R.id.title);
        mShareToQQ = findViewById(R.id.share_to_qq);
        mShareToQQZone = findViewById(R.id.share_to_qqzone);
        mShareToQQSina = findViewById(R.id.share_to_sina);
        mShareToSMS = findViewById(R.id.share_to_mes);
        mShareToEmail = findViewById(R.id.share_to_email);
        mOther = findViewById(R.id.other);

        mTitle.setOnClickListener(this);
        mShareToQQ.setOnClickListener(this);
        mShareToQQZone.setOnClickListener(this);
        mShareToQQSina.setOnClickListener(this);
        mShareToSMS.setOnClickListener(this);
        mShareToEmail.setOnClickListener(this);
        mOther.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title:
                break;
            case R.id.share_to_qq:
                mShareListener.shareToQQ();
                break;
            case R.id.share_to_qqzone:
                mShareListener.shareToQQZone();
                break;
            case R.id.share_to_sina:
                mShareListener.shareToSina();
                break;
            case R.id.share_to_mes:
                mShareListener.shareToSMS();
                break;
            case R.id.share_to_email:
                mShareListener.shareToEmail();
                break;
            case R.id.other:
                break;
        }
    }
    public interface ShareListener{
        public void shareToQQ();
        public void shareToQQZone();
        public void shareToSina();
        public void shareToSMS();
        public void shareToEmail();
    }
    public  void setShareListener(ShareListener shareListener){
        this.mShareListener = shareListener;
    }
}
