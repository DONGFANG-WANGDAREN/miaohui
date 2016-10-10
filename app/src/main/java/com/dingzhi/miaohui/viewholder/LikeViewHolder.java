package com.dingzhi.miaohui.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.enity.WoBaoTaEnity;
import com.dingzhi.miaohui.util.glideutil.GlideUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by SRDZ on 2016/9/8.
 */
public class LikeViewHolder extends BaseViewHolder<WoBaoTaEnity> {

    private ImageView img_head;
    private TextView tv_date,tv_name;
    public LikeViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_like);
        img_head = $(R.id.img_head);
        tv_date = $(R.id.tv_date);
        tv_name = $(R.id.tv_name);
    }

    @Override
    public void setData(WoBaoTaEnity data) {
        super.setData(data);
        GlideUtils.getInstance().loadRoundImageView(getContext(),data.getImghead(),img_head);
        tv_name.setText(data.getName());
        tv_date.setText("喜欢于"+data.getDate());
    }
}
