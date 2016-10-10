package com.dingzhi.miaohui.ui.activity.myinfo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.widget.SqqSwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.switch_sound)
    SqqSwitchButton switchSound;
    @BindView(R.id.switch_shock)
    SqqSwitchButton switchShock;
    @Override
    protected int setLayout() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("消息通知");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        switchSound.setToggleOn(false);
        switchSound.setOnToggleChanged(new SqqSwitchButton.OnToggleChanged() {

            @Override
            public void onToggle(boolean on) {
                Toast.makeText(NotificationActivity.this, "" + (on ? "打开" : "关闭"), Toast.LENGTH_SHORT).show();
            }
        });
        switchShock.setToggleOn(false);
        switchShock.setOnToggleChanged(new SqqSwitchButton.OnToggleChanged() {

            @Override
            public void onToggle(boolean on) {
                Toast.makeText(NotificationActivity.this, "" + (on ? "打开" : "关闭"), Toast.LENGTH_SHORT).show();
            }
        });

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
