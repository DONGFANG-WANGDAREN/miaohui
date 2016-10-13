package com.dingzhi.miaohui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dingzhi.miaohui.R;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名：CheckSelectWhiteAdapter.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:37.
 * 功能描述: 报价对话框 技能选择
 * 函数/方法说明:
 */
public class CheckSelectWhiteAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private HashMap<Integer, String> hashMap;

    public CheckSelectWhiteAdapter(Context context, List<String> list, HashMap<Integer, String> hashMap) {
        this.context = context;
        this.list = list;
        this.hashMap = hashMap;
    }

    public void change(List<String> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void change(List<String> list, HashMap<Integer, String> hashMap) {
        this.list = list;
        this.hashMap = hashMap;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_check_baojia_select, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        if (!isBlank(hashMap.get(position))) {
            holder.textBack.setBackgroundColor(Color.WHITE);
        } else {
            holder.textBack.setBackgroundColor(Color.TRANSPARENT);
        }
        holder.textItem.setText(list.get(position)+"");
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.text_item)
        TextView textItem;
        @BindView(R.id.text_back)
        TextView textBack;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public static boolean isBlank(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
