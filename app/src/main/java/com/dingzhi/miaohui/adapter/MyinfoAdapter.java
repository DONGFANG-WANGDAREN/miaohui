package com.dingzhi.miaohui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.enity.MyInfoEnity;

import java.util.List;

/**
 * Created by SRDZ on 2016/9/6.
 */
public class MyinfoAdapter extends BaseAdapter{
    private Context context;
    private List<MyInfoEnity> datas;
    private LayoutInflater minflatar;

    public MyinfoAdapter(Context context,List<MyInfoEnity> datas){
        this.context = context;
        this.datas = datas;
        minflatar = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null ;
        if (convertView==null){
            holder = new MyViewHolder();
            convertView = minflatar.inflate(R.layout.item_lv_myinfo,null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else {
            holder = (MyViewHolder) convertView.getTag();
        }
        MyInfoEnity myinfo = datas.get(position);
        holder.tv.setText(myinfo.tv);
        holder.img.setBackgroundResource(myinfo.img);
        return convertView;
    }


    public class MyViewHolder{
        public TextView tv;
        public ImageView img;
    }
}
