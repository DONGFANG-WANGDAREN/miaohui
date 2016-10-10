package com.dingzhi.miaohui.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.enity.WoBaoTaEnity;
import com.dingzhi.miaohui.util.glideutil.GlideUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by SRDZ on 2016/9/1.
 */
public class WoBaoTaViewHolder extends BaseViewHolder<WoBaoTaEnity> {
    private ImageView img_head,img_sex;
    private TextView tv_type,tv_date,tv_baojia,tv_age,tv_name;

    public WoBaoTaViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_wobaota);
        img_head = $(R.id.img_head);
        img_sex = $(R.id.img_sex);
        tv_type = $(R.id.tv_type);
        tv_date = $(R.id.tv_date);
        tv_baojia = $(R.id.tv_baojia);
        tv_age = $(R.id.tv_age);
        tv_name = $(R.id.tv_name);
    }

    @Override
    public void setData(WoBaoTaEnity data) {
        super.setData(data);
        tv_name.setText(data.getName());
        tv_age.setText(data.getAge()+"");
        tv_baojia.setText(data.getBaojia()+"");
        tv_date.setText(data.getDate());
        switch (data.getType()){
            case 1:
                tv_type.setText("待服务");
                break;
            case 2:
                tv_type.setText("已同意");
                break;
            case 3:
                tv_type.setText("已拒绝");
                break;
        }
        if (data.getSex()==1){
            img_sex.setBackgroundResource(R.mipmap.icon_nan);
        }else {
            img_sex.setBackgroundResource(R.mipmap.icon_nv);
        }
        GlideUtils.getInstance().loadRoundImageView(getContext(),data.getImghead(),img_head);
    }
}
