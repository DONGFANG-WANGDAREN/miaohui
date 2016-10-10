package com.dingzhi.miaohui.ui.activity.loginorres;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetpasswordActivity extends BaseActivity{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected int setLayout() {
        return R.layout.activity_forgetpassword;
    }

    protected void initView() {
        toolbar.setTitle("忘记密码");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @OnClick(R.id.btn_next)
    public void onClick() {
        startActivity(new Intent(this, SetPasswordActivity.class));
    }
}
