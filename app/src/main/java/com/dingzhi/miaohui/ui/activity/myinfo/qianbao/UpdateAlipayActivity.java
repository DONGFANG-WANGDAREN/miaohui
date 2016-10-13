package com.dingzhi.miaohui.ui.activity.myinfo.qianbao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文件名：UpdateAlipayActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 15:55.
 * 功能描述:修改支付宝
 * 函数/方法说明:
 */
public class UpdateAlipayActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_updateAlipay)
    TextView tvUpdateAlipay;

    @Override
    protected int setLayout() {
        return R.layout.activity_update_alipay;
    }

    protected void initView() {
        toolbar.setTitle("修改支付宝");
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

    @OnClick(R.id.tv_updateAlipay)
    public void onClick() {
        //绑定支付宝
        startActivity(new Intent(this,BindAliPayActivity.class));
    }
}
