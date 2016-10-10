package com.dingzhi.miaohui.ui.activity.myinfo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.enity.WoBaoTaEnity;
import com.dingzhi.miaohui.util.glideutil.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaojiaDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_huodong)
    TextView tvHuodong;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.img_sex)
    ImageView imgSex;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_baojia)
    TextView tvBaojia;
    @BindView(R.id.btn_liaotian)
    Button btnLiaotian;

    @Override
    protected int setLayout() {
        return R.layout.activity_baojia_detail;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("报价详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WoBaoTaEnity woZuTaEnity = getIntent().getParcelableExtra("wozutaenity");
        tvName.setText(woZuTaEnity.getName());
        tvAge.setText(woZuTaEnity.getAge() + "");
        tvBaojia.setText(woZuTaEnity.getBaojia() + "");
        tvHuodong.setText(woZuTaEnity.getHuodong());
        GlideUtils.getInstance().loadRoundImageView(this, woZuTaEnity.getImghead(), imgHead);
        if (woZuTaEnity.getSex() == 1) {
            imgSex.setBackgroundResource(R.mipmap.icon_nan);
        } else {
            imgSex.setBackgroundResource(R.mipmap.icon_nv);
        }
        switch (woZuTaEnity.getType()) {
            case 1:
                tvType.setText("待服务");
                btnLiaotian.setVisibility(View.GONE);
                break;
            case 2:
                tvType.setText("已同意");
                btnLiaotian.setVisibility(View.VISIBLE);
                break;
            case 3:
                tvType.setText("已拒绝");
                btnLiaotian.setVisibility(View.GONE);
                break;
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
