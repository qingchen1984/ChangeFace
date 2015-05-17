package com.changeface.swb.changeface.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.changeface.swb.changeface.R;
import com.changeface.swb.changeface.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ChangeFaceActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton  mBack, mCamera, mTakePhoto;
    private ImageView mImg;
    private String mImgPath = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_face);
        mImgPath = getIntent().getStringExtra("image");
        initViews();
    }

    private void initViews() {
        mImg = (ImageView) findViewById(R.id.img);
//        try {
//           mImg.setImageBitmap(((BitmapDrawable) loadImageFromUrl(mImgPath)).getBitmap());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if (mImgPath != null) {
            ImageLoader.getInstance().displayImage(mImgPath, mImg, ImageLoaderUtil.getImageOptions(R.drawable.image_loaded_by_default));
        }
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.start_fadescale_in);
        mImg.startAnimation(animation);
        mBack = (ImageButton) findViewById(R.id.back);
        mCamera = (ImageButton) findViewById(R.id.camera);
        mTakePhoto = (ImageButton) findViewById(R.id.take_photo);
        mBack.setOnClickListener(this);
        mCamera.setOnClickListener(this);
        mTakePhoto.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_face, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.camera:
                break;
            case R.id.take_photo:
                break;
        }
    }

    public static Drawable loadImageFromUrl(String url) throws IOException {

        URL m = new URL(url);
        InputStream i = (InputStream) m.getContent();
        Drawable d = Drawable.createFromStream(i, "src");
        return d;
    }
}
