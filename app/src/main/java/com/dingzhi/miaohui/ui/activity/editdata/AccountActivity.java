package com.dingzhi.miaohui.ui.activity.editdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.util.C;
import com.dingzhi.miaohui.util.DialogUtil;
import com.dingzhi.miaohui.util.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:账户信息 <br>
 *
 * @auther TX <br>
 * created at 2016/9/5 15:43
 */
public class AccountActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.lay_name)
    RelativeLayout layName;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.lay_birthday)
    RelativeLayout layBirthday;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.lay_phone)
    RelativeLayout layPhone;
    @BindView(R.id.tv_modify)
    TextView tvModify;

    @Override
    protected int setLayout() {
        return R.layout.activity_account;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("账户信息");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String tv_names = intent.getStringExtra("tv_name");
        tvName.setText(tv_names);
        String tv_age = intent.getStringExtra("tv_age");
        tvBirthday.setText(tv_age);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                try {
                    String tv_names = tvName.getText().toString().trim();
                    String tv_age = TimeUtil.getAge(TimeUtil.StringToDate(tvBirthday.getText().toString().trim())) + "";
                    Intent intent = new Intent();
                    intent.putExtra("tv_names", tv_names);
                    intent.putExtra("tv_age", tv_age);
                    AccountActivity.this.setResult(RESULT_OK, intent);
                    AccountActivity.this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case C.REQUEST_CODE_NAME:
                if (resultCode == RESULT_OK) {
                    tvName.setText(data.getExtras().getString("tv"));
                }
                break;
            case C.REQUEST_CODE_YANZHENPHONE:
                if (resultCode == RESULT_OK) {
                    tvPhone.setText(data.getExtras().getString("phone"));
                }
                break;
        }
    }

    @OnClick({R.id.lay_name, R.id.lay_birthday, R.id.lay_phone, R.id.tv_modify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lay_name:
                Intent intent = new Intent(this, WeiXinActivity.class);
                intent.putExtra("tv", tvName.getText().toString().trim());
                intent.putExtra("title", "昵称");
                startActivityForResult(intent, C.REQUEST_CODE_NAME);
                break;
            case R.id.lay_birthday:
                DialogUtil.showDialogDate(tvBirthday, this);
                break;
            case R.id.lay_phone:
                Intent intent1 = new Intent(this, VerificationActivity.class);
                intent1.putExtra("phone", "15879107475");
                startActivityForResult(intent1, C.REQUEST_CODE_YANZHENPHONE);
                break;
            case R.id.tv_modify:
                startActivity(new Intent(this, ModifyActivity.class));
                break;
        }
    }
}
