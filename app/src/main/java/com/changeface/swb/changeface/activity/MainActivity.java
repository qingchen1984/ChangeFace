package com.changeface.swb.changeface.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.changeface.swb.changeface.R;
import com.changeface.swb.changeface.adapter.HomePageAdater;
import com.changeface.swb.changeface.constant.AppConstants;
import com.changeface.swb.changeface.entity.HomePage;
import com.changeface.swb.changeface.entity.HomePageList;
import com.changeface.swb.changeface.view.paginggridview.PagingGridView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener
                ,View.OnClickListener{
    private Toolbar mToolbar;
    private View mLoading;
    private PagingGridView mPagingGridView;
    private HomePageAdater mHomePageAdater;
    private List<HomePage> mHomePages = new ArrayList<>();
    private AsyncHttpClient mAsyncHttpClient;
    private int mPage = 1;
    private ImageView mTop;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case AppConstants.MSG_REQUEST_INFO:
                    getInfoFromServer();
                    break;
                case AppConstants.MSG_SUCCESS:
                    mLoading.setVisibility(View.GONE);
                    initDatas(msg.obj.toString());
                    break;
                case AppConstants.MSG_FAILURE:
                    Toast.makeText(mActivity, R.string.network_unavailable, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
   private void initView(){
       mToolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(mToolbar);
       mToolbar.setNavigationIcon(R.drawable.icon_active_face);
       mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(mActivity,ChangeFaceHomeActivity.class);
               startActivity(intent);
           }
       });
       getSupportActionBar().setDisplayShowTitleEnabled(false);
       getSupportActionBar().setDisplayShowCustomEnabled(true);
       View customView = LayoutInflater.from(this).inflate(R.layout.actionbar_custom_title_layout,null);
       ((TextView)customView).setText(R.string.app_name);
       ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(Gravity.CENTER);
       getSupportActionBar().setCustomView(customView,layoutParams);
       mLoading = findViewById(R.id.loading);
       mPagingGridView = (PagingGridView) findViewById(R.id.grid_view);
       mPagingGridView.setOnItemClickListener(this);
       mHomePageAdater = new HomePageAdater(this,mHomePages);
       mPagingGridView.setHasMoreItems(true);
       mPagingGridView.setPagingableListener(new PagingGridView.Pagingable() {
           @Override
           public void onLoadMoreItems() {
               mPage ++;
               mHandler.sendEmptyMessage(AppConstants.MSG_REQUEST_INFO);
           }

           @Override
           public void onScrollStateChanged(int scrollState,int firstVisibleItem) {
               switch (scrollState){
                   case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                       mTop.setVisibility(View.GONE);
                       break;
                   case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                       if(firstVisibleItem == 0) {
                         mTop.setVisibility(View.GONE);
                       }else {
                         mTop.setVisibility(View.VISIBLE);
                       }
                       break;
                   case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                       mTop.setVisibility(View.GONE);
                       break;
               }
           }
       });
       mHandler.sendEmptyMessage(AppConstants.MSG_REQUEST_INFO);
       mTop = (ImageView) findViewById(R.id.top);
       mTop.setOnClickListener(this);
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void getInfoFromServer(){
        if(mAsyncHttpClient == null){
            mAsyncHttpClient = new AsyncHttpClient();
        }
        mAsyncHttpClient.setTimeout(7000);
        RequestParams params = new RequestParams();
        params.put("p",mPage);
        mAsyncHttpClient.get(AppConstants.HOME_PAGE_BASE_URL,params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                mHandler.sendMessage(mHandler.obtainMessage(AppConstants.MSG_SUCCESS,new String(bytes,0,bytes.length)));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                mHandler.sendEmptyMessage(AppConstants.MSG_FAILURE);
            }
        });
    }
    private void initDatas(String json){
        if(json != null && !"".equals(json)) {
            try {
                HomePageList homePageList = new Gson().fromJson(json, HomePageList.class);
                mHomePages.addAll(homePageList.getItems());
                if (mPagingGridView.getAdapter() == null) {
                    mPagingGridView.setAdapter(mHomePageAdater);
                }
                mHomePageAdater.notifyDataSetChanged();
                mPagingGridView.setHasMoreItems(true);
            } catch (Exception e) {
                e.printStackTrace();
                mHandler.sendEmptyMessage(AppConstants.MSG_FAILURE);
            }
        }else{
            mPagingGridView.setHasMoreItems(false);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHomePages.clear();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,ClothesDetailActivity.class);
        intent.putExtra("title",mHomePages.get(i).getTitle());
        intent.putExtra("url",mHomePages.get(i).getUrl());
        if(mHomePages.get(i).getMainImage() != null
                && mHomePages.get(i).getMainImage().getUrl()!= null
                && !"".equals(mHomePages.get(i).getMainImage().getUrl()))
        intent.putExtra("imageUrl",mHomePages.get(i).getMainImage().getUrl());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.top:
                if(mHomePageAdater != null){
                    mPagingGridView.setAdapter(mHomePageAdater);
                }
                mTop.setVisibility(View.GONE);
                break;
        }
    }
}
