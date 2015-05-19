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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.changeface.swb.changeface.R;
import com.changeface.swb.changeface.adapter.ChangeFaceHomeAdapter;
import com.changeface.swb.changeface.constant.AppConstants;
import com.changeface.swb.changeface.entity.ChangeFaceHome;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;


public class ChangeFaceHomeActivity extends BaseActivity implements AdapterView.OnItemClickListener
        , View.OnClickListener {

    private Toolbar mToolbar;
    private View mLoadingLayout;
    private ListView mListView;
    private List<ChangeFaceHome> mHomePages = new ArrayList<>();
    private AsyncHttpClient mAsyncHttpClient;
    private ChangeFaceHomeAdapter mAdapter;
    private View mLoadingError;
    private Button mReloading;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case AppConstants.MSG_REQUEST_INFO:
                    mLoadingError.setVisibility(View.GONE);
                    mLoadingLayout.setVisibility(View.VISIBLE);
                    getInfoFromServer();
                    break;
                case AppConstants.MSG_SUCCESS:
                    mLoadingLayout.setVisibility(View.GONE);
                    initDatas(msg.obj.toString());
                    break;
                case AppConstants.MSG_FAILURE:
                    mLoadingLayout.setVisibility(View.GONE);
                    mLoadingError.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_face_home);
        initViews();
        mHandler.sendEmptyMessage(AppConstants.MSG_REQUEST_INFO);
    }
    private void initViews(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.icon_actionbar_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        View customView = LayoutInflater.from(this).inflate(R.layout.actionbar_custom_title_layout,null);
        ((TextView)customView).setText(R.string.change_face);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(Gravity.CENTER);
        getSupportActionBar().setCustomView(customView,layoutParams);
        mLoadingLayout = findViewById(R.id.loading_layout);
        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new ChangeFaceHomeAdapter(mActivity,mHomePages);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mLoadingError = findViewById(R.id.loading_error);
        mReloading = (Button) findViewById(R.id.reloading);
        mReloading.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_face_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_share) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    private void getInfoFromServer(){
        if(mAsyncHttpClient == null){
            mAsyncHttpClient = new AsyncHttpClient();
        }
        mAsyncHttpClient.setTimeout(7000);
        mAsyncHttpClient.get(AppConstants.CHANGE_FANCE_HOME_URL,new AsyncHttpResponseHandler() {
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
        try {
            List<ChangeFaceHome> list = new Gson().fromJson(json, new TypeToken<List<ChangeFaceHome>>() {
            }.getType());
            mHomePages.addAll(list);
            mAdapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
            mHandler.sendEmptyMessage(AppConstants.MSG_FAILURE);
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,ChangeFaceWebActivity.class);
        intent.putExtra("title",mHomePages.get(i).getTitle());
        intent.putExtra("url",mHomePages.get(i).getUrl());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reloading:
                mHandler.sendEmptyMessage(AppConstants.MSG_REQUEST_INFO);
                break;
        }
    }
}
