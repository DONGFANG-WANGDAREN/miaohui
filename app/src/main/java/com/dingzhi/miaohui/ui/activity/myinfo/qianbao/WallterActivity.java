package com.dingzhi.miaohui.ui.activity.myinfo.qianbao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.util.C;
import com.dingzhi.miaohui.util.PreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文件名：WallterActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 15:56.
 * 功能描述:钱包
 * 函数/方法说明:
 */
public class WallterActivity extends BaseActivity implements OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_income)
    TextView tvIncome;//现金收支明细
    @BindView(R.id.tv_withdraw)
    TextView tvWithdraw;//提现
    @BindView(R.id.tv_bind)
    TextView tvBind;//绑定支付宝
    @BindView(R.id.tv_setting)
    TextView tvSetting;//设置交易密码

    @Override
    protected int setLayout() {
        return R.layout.activity_wallter;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("钱包");
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

    @OnClick({R.id.tv_income, R.id.tv_withdraw, R.id.tv_bind, R.id.tv_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_income: //跳转现金收支明细
                startActivity(new Intent(this, IncomeActivity.class));
                break;
            case R.id.tv_withdraw: //跳转提现
                startActivity(new Intent(this, WithdrawActivity.class));
                break;
            case R.id.tv_bind: //跳转绑定支付宝或是修改支付宝
                String alpayaccount = PreferencesUtil.getString(this, C.FILE_NAME, "alpayaccount");
                String alpayname = PreferencesUtil.getString(this, C.FILE_NAME, "alpayname");
                if (alpayaccount.isEmpty() && alpayname.isEmpty()) {
                    startActivity(new Intent(this, BindAliPayActivity.class));
                } else {
                    startActivity(new Intent(this, UpdateAlipayActivity.class));
                }
                break;
            case R.id.tv_setting: //跳转设置支付密码或者修改支付密码
                String paypassword = PreferencesUtil.getString(this, C.FILE_NAME, "paypassword");
                if (paypassword.isEmpty()) {
                    startActivity(new Intent(this, SetPayPasswordActivity.class));
                } else {
                    startActivity(new Intent(this, UdateOrFindPayActivity.class));
                }
                break;
        }
    }
}
