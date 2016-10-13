package com.dingzhi.miaohui.viewholder;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.enity.MovableEnity;
import com.dingzhi.miaohui.util.glideutil.GlideUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * 文件名：MovableViewHolder.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:21.
 * 功能描述: 活动——viewholder
 * 函数/方法说明:
 */
public class MovableViewHolder extends BaseViewHolder<MovableEnity> {

    private ImageView img_movable;
    private TextView tv_title,tv_contexts;
    public MovableViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_bottom_movable);
        img_movable = $(R.id.img_movable);
        tv_title = $(R.id.tv_title);
        tv_contexts = $(R.id.tv_contexts);
    }

    @Override
    public void setData(MovableEnity data) {
        super.setData(data);
        GlideUtils.getInstance().loadImageView(getContext(),data.getImg(),img_movable);
        tv_title.setText(data.getTitle());
        tv_contexts.setText(data.getContent());
    }
}
