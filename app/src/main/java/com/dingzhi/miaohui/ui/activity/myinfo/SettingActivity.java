package com.dingzhi.miaohui.ui.activity.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.util.AppUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_notification)
    TextView tvNotification;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }

    protected void initView() {
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvVersion.setText("喵会" + AppUtil.getVersionName(this));
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


    @OnClick({R.id.tv_notification, R.id.tv_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_notification:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.tv_clear:
                startActivity(new Intent(this, ClearCacheActivity.class));
                break;
        }
    }
}
