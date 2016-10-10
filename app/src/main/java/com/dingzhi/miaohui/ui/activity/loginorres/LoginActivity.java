package com.dingzhi.miaohui.ui.activity.loginorres;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.ui.activity.MainActivity;
import com.dingzhi.miaohui.util.ExpressionUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity  {


    public SystemBarTintManager mgr;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.text_input_phone)
    TextInputLayout textInputPhone;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.text_input_password)
    TextInputLayout textInputPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forgetpassword)
    TextView tvForgetpassword;
    @BindView(R.id.tv_registered)
    TextView tvRegistered;


    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }


    protected void initView() {
        textInputPhone.getEditText().addTextChangedListener(phone);
        textInputPassword.getEditText().addTextChangedListener(password);
    }


    private TextWatcher phone = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (ExpressionUtil.isMobileNO(s.toString())) {
                textInputPhone.setErrorEnabled(false);
            } else {
                textInputPhone.setError("请输入正确手机号");
                textInputPhone.setErrorEnabled(true);

            }
        }
    };

    private TextWatcher password = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() < 21 && s.length() > 6) {
                textInputPassword.setErrorEnabled(false);
            } else {
                textInputPassword.setError("密码必须为6～20位字符");
                textInputPassword.setErrorEnabled(true);

            }
        }
    };


    @OnClick({R.id.btn_login, R.id.tv_forgetpassword, R.id.tv_registered})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.tv_forgetpassword:
                startActivity(new Intent(this, ForgetpasswordActivity.class));
                break;
            case R.id.tv_registered:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
        }
    }
}
