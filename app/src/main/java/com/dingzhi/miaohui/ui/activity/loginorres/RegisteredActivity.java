package com.dingzhi.miaohui.ui.activity.loginorres;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文件名：RegisteredActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 15:05.
 * 功能描述: 注册
 * 函数/方法说明:
 */
public class RegisteredActivity extends BaseActivity  {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected int setLayout() {
        return R.layout.activity_registered;
    }

    protected void initView() {
        toolbar.setTitle("注册");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * 函数名：  onOptionsItemSelected
     * 创建人： TanXin.
     * 创建日期： 2016/10/13 14:55.
     * 功能描述：toolbar返回键
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


    @OnClick(R.id.btn_next)
    public void onClick() {
        //跳转至我的信息界面
        startActivity(new Intent(this, MyInfoNameActivity.class));
    }
}
