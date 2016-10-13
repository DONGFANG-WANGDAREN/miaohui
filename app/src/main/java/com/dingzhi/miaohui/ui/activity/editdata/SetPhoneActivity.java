package com.dingzhi.miaohui.ui.activity.editdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文件名：SetPhoneActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 15:42.
 * 功能描述: 设置手机
 * 函数/方法说明:
 */
public class SetPhoneActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_phone_two)
    EditText edPhoneTwo;
    @BindView(R.id.btn_sure)
    Button btnSure;

    @Override
    protected int setLayout() {
        return R.layout.activity_set_phone;
    }

    protected void initView() {
        toolbar.setTitle("设置手机");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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



    @OnClick(R.id.btn_sure)
    public void onClick() {
        //手机号回调
        String tv_phone = edPhone.getText().toString().trim();
        if (tv_phone.isEmpty()) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("tv_phone", tv_phone);
            SetPhoneActivity.this.setResult(RESULT_OK, intent);
            SetPhoneActivity.this.finish();
        }
    }
}
