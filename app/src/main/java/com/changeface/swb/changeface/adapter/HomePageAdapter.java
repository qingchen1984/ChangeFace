package com.changeface.swb.changeface.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeface.swb.changeface.R;
import com.changeface.swb.changeface.entity.HomePage;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-11
 * Time: 23:11
 */
public class HomePageAdapter extends DataBaseAdapter<HomePage>{


    public HomePageAdapter(Context context, List<HomePage> list) {
        super(context, list);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_list_layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(mList.get(position).getImg(),viewHolder.img,options);
        viewHolder.time.setText(mList.get(position).getTime());
        viewHolder.title.setText(mList.get(position).getTitle());
        return convertView;
    }
    static class ViewHolder{
        ImageView img;
        TextView time,title;
    }
}
