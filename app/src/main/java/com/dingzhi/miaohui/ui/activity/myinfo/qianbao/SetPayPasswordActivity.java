package com.dingzhi.miaohui.ui.activity.myinfo.qianbao;

import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.util.C;
import com.dingzhi.miaohui.util.PreferencesUtil;
import com.dingzhi.miaohui.widget.weixin_keyboard.PopEnterPassword;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:设置支付密码 <br>
 *
 * @auther TX <br>
 * created at 2016/9/12 13:51
 */
public class SetPayPasswordActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_phone)
    TextView tv_Phone;
    @BindView(R.id.ed_code)
    EditText ed_Code;
    @BindView(R.id.btn_code)
    Button btn_Code;
    @BindView(R.id.btn_verification)
    Button btn_Verification;



    @Override
    protected int setLayout() {
        return R.layout.activity_set_pay_password;
    }

    protected void initView() {
        toolbar.setTitle("设置支付密码");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick({R.id.btn_code, R.id.btn_verification})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_code:
                break;
            case R.id.btn_verification:
                FirstPassword();
                break;
        }
    }


    private void FirstPassword(){
        //第一次输入的密码
        PopEnterPassword popEnterPassword = new PopEnterPassword(this,"请输入密码");

        // 显示窗口
        popEnterPassword.showAtLocation(this.findViewById(R.id.layoutContent),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置

        popEnterPassword.setCallback(new PopEnterPassword.Callback() {
            @Override
            public void getpassword(String password) {
               SecondPassword(password);
            }
        });
    }

    private void SecondPassword(final String firstpassword){
        //第二次输入的密码
        PopEnterPassword popEnterPassword = new PopEnterPassword(this,"请再次确认密码");

        // 显示窗口
        popEnterPassword.showAtLocation(this.findViewById(R.id.layoutContent),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置

        popEnterPassword.setCallback(new PopEnterPassword.Callback() {
            @Override
            public void getpassword(String password) {
                if (password.equals(firstpassword)){
                    PreferencesUtil.putString(SetPayPasswordActivity.this, C.FILE_NAME,"paypassword",password);
                    finish();
                    Toast.makeText(SetPayPasswordActivity.this,"输入完成"+password, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SetPayPasswordActivity.this,"两个密码不相同，请再次输入", Toast.LENGTH_SHORT).show();
                }

            }
        });
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
