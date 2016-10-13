package com.dingzhi.miaohui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.widget.DragSelectRecyclerViewAdapter;

import java.util.List;

/**
 * 文件名：SkillAdapter.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:25.
 * 功能描述: 技能服务 Adapter
 * 参考资料：https://github.com/afollestad/drag-select-recyclerview
 * 函数/方法说明:
 */
public class SkillAdapter extends DragSelectRecyclerViewAdapter<SkillAdapter.SkillViewHolder> {

    public interface ClickListener {
        void onClick(int index);
    }

    private List<String> mlist;
    private ClickListener mCallback;
    public void setCallback(ClickListener callback){
        this.mCallback = callback;
    }

    public SkillAdapter( List<String> list) {
        super();
        mlist = list;
    }

    public String getItem(int index) {
        return mlist.get(index);
    }

    @Override
    public SkillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill, parent, false);
        return new SkillViewHolder(v, mCallback);
    }

    @Override
    public void onBindViewHolder(SkillViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tv.setText(getItem(position));

        if (isIndexSelected(position)) {
            holder.tv.setBackgroundResource(R.mipmap.icon_skill_select);
            holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.tv.setBackgroundResource(R.mipmap.icon_skill);
            holder.tv.setTextColor(Color.parseColor("#FF596E"));
        }

    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public static class SkillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv;
        private ClickListener mCallback;

        public SkillViewHolder(View itemView,ClickListener callback) {
            super(itemView);
            mCallback = callback;
            this.tv = (TextView) itemView.findViewById(R.id.tv);
            this.itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mCallback != null)
                mCallback.onClick(getAdapterPosition());
        }
    }
}
