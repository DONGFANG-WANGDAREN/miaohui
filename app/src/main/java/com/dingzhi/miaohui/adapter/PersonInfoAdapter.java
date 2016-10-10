package com.dingzhi.miaohui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.enity.PersonInfoEnity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SRDZ on 2016/9/19.
 */
public class PersonInfoAdapter extends BaseAdapter {
    private Context context;
    private List<PersonInfoEnity> list;

    public PersonInfoAdapter(Context context, List<PersonInfoEnity> list) {
        this.context = context;
        this.list = list;
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
        if (view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_person_info, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvName.setText(list.get(position).getInfoname());
        holder.tvContext.setText(list.get(position).getInfocontext());
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_context)
        TextView tvContext;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
