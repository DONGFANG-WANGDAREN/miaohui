package com.dingzhi.miaohui.ui.activity.loginorres;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

 /**
  * 文件名：MyInfoNameActivity.
  * 版权所有：SRDZ
  * 创建人：TANXIN
  * 创建日期:2016/10/13 15:00.
  * 功能描述: 注册—个人信息
  * 函数/方法说明:
  */
public class MyInfoNameActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.btn_next)
    Button btnNext;
    private int mSex = -1;

    @Override
    protected int setLayout() {
        return R.layout.activity_my_info_name;
    }

    protected void initView() {
        toolbar.setTitle("个人信息");
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

      /**
        * 函数名： showDialogSex
        * 创建人： TanXin.
        * 创建日期： 2016/10/13 15:01.
        * 功能描述：打开选择新别参数框
        * 参数列表：TextView tvSex
        */
    private void showDialogSex(final TextView timeText) {
        String[] sexs = {"男", "女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MyInfoNameActivity.this,R.style.MyDialog);
        builder.setTitle("选择性别").setSingleChoiceItems(sexs, mSex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyInfoNameActivity.this.mSex = i;
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mSex == -1){
                    Toast.makeText(MyInfoNameActivity.this, "请选择性别", Toast.LENGTH_SHORT).show();
                }else if (mSex == 1) {
                    timeText.setText("女");
                } else {
                    timeText.setText("男");
                }
                timeText.setTextColor(Color.parseColor("#4c4c4c"));
            }
        });
        builder.create();
        builder.show();
    }

    @OnClick({R.id.tv_date, R.id.tv_sex, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_date:  //打开选择日期对话框
                DialogUtil.showDialogDate(tvDate, this);
                break;
            case R.id.tv_sex:   //打开选择性别对话框
                showDialogSex(tvSex);
                break;
            case R.id.btn_next: //下一步——跳转至上传头像
                String name = edName.getText().toString().trim();
                String date = tvDate.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(MyInfoNameActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();

                } else if (date.isEmpty()) {
                    Toast.makeText(MyInfoNameActivity.this, "请选择你的出生日期", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, UploadimgActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("date", date);
                    startActivity(intent);
                }
                break;
        }
    }
}