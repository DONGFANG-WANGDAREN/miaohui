package com.dingzhi.miaohui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.enity.WoBaoTaEnity;
import com.dingzhi.miaohui.util.glideutil.GlideUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by SRDZ on 2016/9/1.
 */
public class TaBaoWoViewHolder extends BaseViewHolder<WoBaoTaEnity> {
    private ImageView img_head,img_sex;
    private TextView tv_type,tv_date,tv_baojia,tv_age,tv_name,tv_sure,tv_jujue;
    private LinearLayout lay_weichuli;

    public TaBaoWoViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_tabaowo);
        img_head = $(R.id.img_head);
        img_sex = $(R.id.img_sex);
        tv_type = $(R.id.tv_type);
        tv_date = $(R.id.tv_date);
        tv_baojia = $(R.id.tv_baojia);
        tv_age = $(R.id.tv_age);
        tv_name = $(R.id.tv_name);
        tv_sure = $(R.id.tv_sure);
        tv_jujue = $(R.id.tv_jujue);
        lay_weichuli = $(R.id.lay_weichuli);
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
                lay_weichuli.setVisibility(View.VISIBLE);
                tv_type.setVisibility(View.GONE);
                break;
            case 2:
                lay_weichuli.setVisibility(View.GONE);
                tv_type.setVisibility(View.VISIBLE);
                tv_type.setText("已同意");
                break;
            case 3:
                lay_weichuli.setVisibility(View.GONE);
                tv_type.setVisibility(View.VISIBLE);
                tv_type.setText("已拒绝");
                break;
        }
        if (data.getSex()==1){
            img_sex.setBackgroundResource(R.mipmap.icon_nan);
        }else {
            img_sex.setBackgroundResource(R.mipmap.icon_nv);
        }
        GlideUtils.getInstance().loadRoundImageView(getContext(),data.getImghead(),img_head);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "sure", Toast.LENGTH_SHORT).show();
            }
        });
        tv_jujue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "jujue", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
