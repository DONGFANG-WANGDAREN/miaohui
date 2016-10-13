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

/**
 * 文件名：NotificationActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 16:05.
 * 功能描述: 消息通知
 * 函数/方法说明:
 */
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
            case android.R.id.home: //返回
                finish();
                break;
        }
        return true;
    }

}
