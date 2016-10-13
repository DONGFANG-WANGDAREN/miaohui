package com.dingzhi.miaohui.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.enity.IncomeEnity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * 文件名：InComeViewHolder.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:17.
 * 功能描述: 现金收支明细——ViewHolder
 * 函数/方法说明:
 */
public class InComeViewHolder extends BaseViewHolder<IncomeEnity> {
    private TextView tv_type,tv_huodong,tv_money,tv_date;
   
    public InComeViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_income);
        tv_type = $(R.id.tv_type);
        tv_huodong = $(R.id.tv_huodong);
        tv_money = $(R.id.tv_money);
        tv_date = $(R.id.tv_date);
    }

    @Override
    public void setData(IncomeEnity data) {
        super.setData(data);
        switch (data.getType()){
            case 1:
                tv_type.setText("收入:");
                tv_money.setText("+"+data.getMoney());
                tv_huodong.setVisibility(View.VISIBLE);
                tv_huodong.setText(data.getHuodong());
                break;
            case 2:
                tv_type.setText("支出:");
                tv_money.setText("-"+data.getMoney());
                tv_huodong.setVisibility(View.VISIBLE);
                tv_huodong.setText(data.getHuodong());
                break;
            case 3:
                tv_type.setText("退款:");
                tv_money.setText("+"+data.getMoney());
                tv_huodong.setVisibility(View.VISIBLE);
                tv_huodong.setText(data.getHuodong());
                break;
            case 4:
                tv_type.setText("提现余额:");
                tv_huodong.setVisibility(View.GONE);
                tv_money.setText("-"+data.getMoney());
                break;
        }
        tv_date.setText(data.getDate());
    }
}
