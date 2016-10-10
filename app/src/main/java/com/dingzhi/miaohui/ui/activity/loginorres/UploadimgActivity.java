package com.dingzhi.miaohui.ui.activity.loginorres;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.util.C;
import com.dingzhi.miaohui.util.SelectPicActivity;
import com.dingzhi.miaohui.util.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UploadimgActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    private String name, date;

    @Override
    protected int setLayout() {
        return R.layout.activity_uploadimg;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");
        toolbar.setTitle("个人信息");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            tvAge.setText(TimeUtil.getAge(TimeUtil.StringToDate(date)) + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvName.setText(name);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case C.REQUEST_CODE_IMG01:
                if (data != null) {
                    Glide.with(this).load(data.getStringExtra(SelectPicActivity.KEY_PHOTO_PATH)).into(imgHead);
                }
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

    @OnClick(R.id.img_head)
    public void onClick() {
        Intent PicIntent1 = new Intent(UploadimgActivity.this, SelectPicActivity.class);
        startActivityForResult(PicIntent1, C.REQUEST_CODE_IMG01);
    }
}
