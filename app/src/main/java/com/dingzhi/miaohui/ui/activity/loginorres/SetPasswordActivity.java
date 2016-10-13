package com.dingzhi.miaohui.ui.activity.loginorres;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文件名：SetPasswordActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 14:57.
 * 功能描述: 设置密码
 * 函数/方法说明:
 */
public class SetPasswordActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.btn_sure)
    Button btnSure;

    @Override
    protected int setLayout() {
        return R.layout.activity_set_password;
    }

    protected void initView() {
        toolbar.setTitle("设置密码");
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

    @OnClick(R.id.btn_sure)
    public void onClick() {
    }
}
