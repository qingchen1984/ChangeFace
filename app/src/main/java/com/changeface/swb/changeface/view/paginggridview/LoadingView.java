package com.changeface.swb.changeface.view.paginggridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.changeface.swb.changeface.R;


public class LoadingView extends LinearLayout {
    private ProgressBar mProgressBar;
    private TextView mTextView;
	public LoadingView(Context context) {
		super(context);
		init();
	}

	public LoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
        View view = inflate(getContext(), R.layout.loading_view, this);
        mProgressBar = (ProgressBar) view.findViewById(R.id.loading_progress_bar);
        mTextView = (TextView) view.findViewById(R.id.loading_text);
    }

    public void setLoadingText(String text){
        mTextView.setText(text);
    }

    public void setLoadingText(int srcId){
        mTextView.setText(getResources().getString(srcId));
    }
    public void setShowProgress(boolean isShow){
        if(isShow){
            mProgressBar.setVisibility(View.VISIBLE);
        }else{
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
