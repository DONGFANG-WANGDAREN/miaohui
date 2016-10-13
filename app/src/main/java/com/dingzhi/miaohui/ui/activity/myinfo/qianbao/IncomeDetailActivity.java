package com.dingzhi.miaohui.ui.activity.myinfo.qianbao;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.enity.IncomeEnity;
import com.dingzhi.miaohui.widget.UnderLineLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名：IncomeDetailActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 15:49.
 * 功能描述: 收支详情
 * 函数/方法说明:
 */
public class IncomeDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_tixian_type)
    TextView tvTixianType;
    @BindView(R.id.tv_type_time)
    TextView tvTypeTime;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_skill)
    TextView tvSkill;
    @BindView(R.id.lay_one)
    LinearLayout layOne;
    @BindView(R.id.tv_tixian_money)
    TextView tvTixianMoney;
    @BindView(R.id.underline_layout)
    UnderLineLinearLayout underlineLayout;
    @BindView(R.id.lay_two)
    LinearLayout layTwo;

    @Override
    protected int setLayout() {
        return R.layout.activity_income_detail;
    }

    @Override
    protected void initView() {
        IncomeEnity income = getIntent().getParcelableExtra("income");
        switch (income.getType()) {
            case 1:
                toolbar.setTitle("收入余额详情");
                tvType.setText("收入至余额:");
                layOne.setVisibility(View.VISIBLE);
                layTwo.setVisibility(View.GONE);
                tvTypeTime.setText("收入时间:");
                tvDate.setText(income.getDate());
                tvTime.setText(income.getTime());
                tvMoney.setText("￥" + income.getMoney() + "");
                break;
            case 2:
                toolbar.setTitle("支出余额详情");
                tvType.setText("支出至余额:");
                layOne.setVisibility(View.VISIBLE);
                layTwo.setVisibility(View.GONE);
                tvTypeTime.setText("支出时间:");
                tvDate.setText(income.getDate());
                tvTime.setText(income.getTime());
                tvMoney.setText("￥" + income.getMoney() + "");
                break;
            case 3:
                toolbar.setTitle("退款余额详情");
                tvType.setText("退款至余额:");
                layOne.setVisibility(View.VISIBLE);
                layTwo.setVisibility(View.GONE);
                tvTypeTime.setText("退款时间:");
                tvDate.setText(income.getDate());
                tvTime.setText(income.getTime());
                tvMoney.setText("￥" + income.getMoney());
                break;
            case 4:
                toolbar.setTitle("提现详情");
                tvType.setText("提现余额");
                layOne.setVisibility(View.GONE);
                layTwo.setVisibility(View.VISIBLE);
                tvMoney.setText("￥" + income.getMoney());
                tvTixianMoney.setText("￥" + income.getMoney());
                break;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        switch (income.getMoneytype()) {
            case 0:
                tvTixianType.setVisibility(View.GONE);
                break;
            case 1:
                tvTixianType.setVisibility(View.VISIBLE);
                tvTixianType.setText("处理中");
                Checking();
                break;
            case 2:
                tvTixianType.setVisibility(View.VISIBLE);
                tvTixianType.setText("提现成功");
                Checkout();
                break;
        }

        if (income.getHuodongtype() == 1) {
            tvSkill.setText("线上-" + income.getHuodong());
        } else if (income.getHuodongtype() == 2) {
            tvSkill.setText("线下-" + income.getHuodong());
        } else {
            return;
        }
    }




    private void Checking() { //审核中
        View v = LayoutInflater.from(this).inflate(R.layout.item_timelinel, underlineLayout, false);
        TextView tv = (TextView) v.findViewById(R.id.tv_title);
        tv.setText("审核中");
        tv.setTextColor(Color.parseColor("#ff596e"));
        ((TextView) v.findViewById(R.id.tv_context)).setText("金额会在3-5个工作日转到您的支付宝账号");
        ((TextView) v.findViewById(R.id.tv_date)).setText("2016-01-21");
        ((TextView) v.findViewById(R.id.tv_time)).setText("12:20:23");
        underlineLayout.addView(v);

    }

    private void Checkout() { //审核通过
        View v = LayoutInflater.from(this).inflate(R.layout.item_timelinel, underlineLayout, false);
        ((TextView) v.findViewById(R.id.tv_title)).setText("审核通过");
        ((TextView) v.findViewById(R.id.tv_context)).setText("金额会在3-5个工作日转到您的支付宝账号");
        ((TextView) v.findViewById(R.id.tv_date)).setText("2016-01-21");
        ((TextView) v.findViewById(R.id.tv_time)).setText("12:20:23");
        underlineLayout.addView(v);
        View v1 = LayoutInflater.from(this).inflate(R.layout.item_timelinel, underlineLayout, false);
        TextView tv = (TextView) v1.findViewById(R.id.tv_title);
        tv.setText("已到帐");
        tv.setTextColor(Color.parseColor("#ff596e"));
        ((TextView) v1.findViewById(R.id.tv_context)).setText("金额会在3-5个工作日转到您的支付宝账号");
        ((TextView) v1.findViewById(R.id.tv_date)).setText("2016-01-21");
        ((TextView) v1.findViewById(R.id.tv_time)).setText("12:20:23");
        underlineLayout.addView(v1);
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
