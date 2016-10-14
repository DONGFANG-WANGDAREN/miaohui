package com.dingzhi.miaohui.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dingzhi.miaohui.R;

/**
 * 文件名：FilterDialog.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 18:02.
 * 功能描述: 筛选对话框
 * 函数/方法说明:
 */
public class FilterDialog extends Dialog implements View.OnClickListener {
    private onButtonClick mButtonClickCallBack;
    private Context context;
    private TextView tv_sex_all,tv_sex_man,tv_sex_woman,tv_age,tv_range,tv_sure,tv_cancel;
    private SeekBar sb_age,sb_range;
    private int sex;
    private int range;
    private int age;

    public interface onButtonClick{
        public void OnSure(int sex,int age,int range);
        public void OnCancel();
    }
    public void setOnButtonClick(onButtonClick mNegativeCallBack) {

        this.mButtonClickCallBack = mNegativeCallBack;

    }
    public FilterDialog(Context context) {
        super(context);
        this.context = context;
    }
    public FilterDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_filter);
        initView();
        initData();
        selectsex(1);
    }

    private void initView() {
        tv_sex_all = (TextView) findViewById(R.id.tv_sex_all);
        tv_sex_man = (TextView) findViewById(R.id.tv_sex_man);
        tv_sex_woman = (TextView) findViewById(R.id.tv_sex_woman);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_range = (TextView) findViewById(R.id.tv_range);
        tv_sure = (TextView) findViewById(R.id.tv_sure);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        sb_age = (SeekBar) findViewById(R.id.sb_age);
        sb_range = (SeekBar) findViewById(R.id.sb_range);
    }

    private void initData() {
        tv_sex_all.setOnClickListener(this);
        tv_sex_man.setOnClickListener(this);
        tv_sex_woman.setOnClickListener(this);
        tv_sure.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        sb_age.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_age.setText((progress+16)+"岁");
                age = progress+16;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb_range.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_range.setText((progress+1)+"km");
                range  = progress+1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sex_all:
                selectsex(1);
            break;
            case R.id.tv_sex_man:
                selectsex(2);
                break;
            case R.id.tv_sex_woman:
                selectsex(3);
                break;
            case R.id.tv_sure:
                mButtonClickCallBack.OnSure(sex,age,range);
                break;
            case R.id.tv_cancel:
                mButtonClickCallBack.OnCancel();
                break;
        }
    }

    public void selectsex(int index){
        switch (index){
            case 1: //不限
                tv_sex_all.setTextColor(Color.parseColor("#ff596e"));
                tv_sex_man.setTextColor(Color.parseColor("#b3b3b3"));
                tv_sex_woman.setTextColor(Color.parseColor("#b3b3b3"));
                sex = 1;
                break;
            case 2: //男
                tv_sex_all.setTextColor(Color.parseColor("#b3b3b3"));
                tv_sex_man.setTextColor(Color.parseColor("#ff596e"));
                tv_sex_woman.setTextColor(Color.parseColor("#b3b3b3"));
                sex = 2;
                break;
            case 3: //女
                tv_sex_all.setTextColor(Color.parseColor("#b3b3b3"));
                tv_sex_man.setTextColor(Color.parseColor("#b3b3b3"));
                tv_sex_woman.setTextColor(Color.parseColor("#ff596e"));
                sex = 3;
                break;

        }
    }
}
