package com.dingzhi.miaohui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.widget.DragSelectRecyclerViewAdapter;

import java.util.List;
/**
 * 文件名：SelectAdapter.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:29.
 * 功能描述: 地区，行业选择多选recycle adapter
 * 参考资料：https://github.com/afollestad/drag-select-recyclerview
 * 函数/方法说明:
 */
public class SelectAdapter extends DragSelectRecyclerViewAdapter<SelectAdapter.SelectViewHolder> {
    private final ClickListener mCallback;
    private List<String> mlist;

    public SelectAdapter(ClickListener callback,List<String> list) {
        super();
        mCallback = callback;
        mlist = list;
    }
    public String getItem(int index) {
        return mlist.get(index);
    }

    @Override
    public SelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select, parent, false);
        return new SelectViewHolder(v, mCallback);
    }

    @Override
    public void onBindViewHolder(SelectViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tv.setText(getItem(position));
        if (isIndexSelected(position)) {
            holder.img.setVisibility(View.VISIBLE);
        } else {
            holder.img.setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface ClickListener {
        void onClick(int index);
    }

    public static class SelectViewHolder extends RecyclerView.ViewHolder implements OnClickListener{
        public final TextView tv;
        public ImageView img;
        private final ClickListener mCallback;
        public SelectViewHolder(View itemView, ClickListener callback) {
            super(itemView);
            mCallback = callback;
            this.tv = (TextView) itemView.findViewById(R.id.tv);
            this.img = (ImageView) itemView.findViewById(R.id.img);
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mCallback != null)
                mCallback.onClick(getAdapterPosition());
        }
    }
}
