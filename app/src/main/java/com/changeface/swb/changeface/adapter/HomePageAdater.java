package com.changeface.swb.changeface.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeface.swb.changeface.R;
import com.changeface.swb.changeface.entity.HomePage;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Description:
 * Author: SheWenBiao
 * Date: 2015-05-17
 * Time: 15:50
 */
public class HomePageAdater extends DataBaseAdapter<HomePage>{
    public HomePageAdater(Context context, List<HomePage> list) {
        super(context, list);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item_grid_layout,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(mList.get(position)!= null && mList.get(position).getMainImage()!=null
                && mList.get(position).getMainImage().getSmallUrl()!=null
                && !"".equals(mList.get(position).getMainImage().getSmallUrl())) {
            ImageLoader.getInstance().displayImage(mList.get(position).getMainImage().getSmallUrl(), viewHolder.img, options);
        }
        viewHolder.price.setText(mList.get(position).getPrice()+"");
        return convertView;
    }
    static class ViewHolder{
        ImageView img;
        TextView price;
    }
}
