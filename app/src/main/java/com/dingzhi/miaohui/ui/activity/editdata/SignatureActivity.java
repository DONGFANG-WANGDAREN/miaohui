package com.dingzhi.miaohui.ui.activity.editdata;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文件名：SignatureActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 15:40.
 * 功能描述: 个性签名
 * 函数/方法说明:
 */
public class SignatureActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_signature)
    EditText edSignature;
    @BindView(R.id.lay_signature)
    LinearLayout laySignature;
    @BindView(R.id.tv_length)
    TextView tvLength;
    private boolean isFlag;

    @Override
    protected int setLayout() {
        return R.layout.activity_signature;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("个性签名");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edSignature.addTextChangedListener(tvlength);
        Intent intent = getIntent();
        String tv_signature = intent.getStringExtra("tv_signature");
        edSignature.setText(tv_signature);
    }


    //检测输入个性签名的长度
    private TextWatcher tvlength = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int length = s.length();
            tvLength.setText(length + "");
            if (length > 50) {
                tvLength.setTextColor(Color.parseColor("#ff596e"));
            } else {
                tvLength.setTextColor(Color.parseColor("#b3b3b3"));
            }
        }
        @Override
        public void afterTextChanged(Editable s) {
            int length = s.length();
            if (length > 50) {
                isFlag = false;
            } else {
                isFlag = true;
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //返回
                finish();
                break;
            case R.id.action_complete: //完成
                String tv_signature = edSignature.getText().toString().trim();
                if (tv_signature.isEmpty()) {
                    Toast.makeText(SignatureActivity.this, "请输入个性签名", Toast.LENGTH_SHORT).show();
                } else if (!isFlag) {
                    Toast.makeText(SignatureActivity.this, "个性签名字数必须小于50", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("tv_signature", tv_signature);
                    SignatureActivity.this.setResult(RESULT_OK, intent);
                    SignatureActivity.this.finish();
                }
                break;
        }
        return true;
    }

     /**
       * 函数名： onCreateOptionsMenu
       * 创建人： TanXin.
       * 创建日期： 2016/10/13 15:41.
       * 功能描述：菜单显示完成按钮
       */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_complete, menu);

        return super.onCreateOptionsMenu(menu);

    }
    @OnClick(R.id.lay_signature)
    public void onClick() {
        //让输入框获取焦点，弹出软键盘
        edSignature.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edSignature, InputMethodManager.SHOW_IMPLICIT);
    }
}
