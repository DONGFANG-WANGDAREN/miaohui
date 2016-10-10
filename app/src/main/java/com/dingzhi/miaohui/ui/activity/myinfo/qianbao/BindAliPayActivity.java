package com.dingzhi.miaohui.ui.activity.myinfo.qianbao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.util.C;
import com.dingzhi.miaohui.util.PreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindAliPayActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_account)
    EditText edAccount;
    @BindView(R.id.btn_sure)
    Button btnSure;

    @Override
    protected int setLayout() {
        return R.layout.activity_bind_ali_pay;
    }

    protected void initView() {
        toolbar.setTitle("绑定支付宝");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.btn_sure)
    public void onClick() {
        String alpayaccount= edAccount.getText().toString().trim();
        String alpayname= edName.getText().toString().trim();
        PreferencesUtil.putString(this, C.FILE_NAME,"alpayaccount",alpayaccount);
        PreferencesUtil.putString(this, C.FILE_NAME,"alpayname",alpayname);
        finish();
        Toast.makeText(this, "绑定成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
