package com.dingzhi.miaohui.ui.activity.myinfo.qianbao;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
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
 * 文件名：UdateOrFindPayActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 15:53.
 * 功能描述: 修改或者找回密码
 * 函数/方法说明:
 */
public class UdateOrFindPayActivity extends BaseActivity  {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_update)
    TextView tv_update;
    @BindView(R.id.tv_find)
    TextView tv_find;



    @Override
    protected int setLayout() {
        return R.layout.activity_udate_or_find_pay;
    }


    protected void initView() {
        toolbar.setTitle("设置支付密码");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @OnClick({R.id.tv_update, R.id.tv_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_update:
                BeforePassword(); //掉起支付键盘
                break;
            case R.id.tv_find: //跳转设置支付密码
                startActivity(new Intent(this,SetPayPasswordActivity.class));
                break;
        }
    }

     /**
       * 函数名：BeforePassword
       * 创建人： TanXin.
       * 创建日期： 2016/10/13 15:54.
       * 功能描述：输入原来的密码
       * 参考文档：https://github.com/zuiwuyuan/WeChatPswKeyboard
       */
    private void BeforePassword(){
        //原来的密码
        PopEnterPassword popEnterPassword = new PopEnterPassword(this,"请输入原密码");
        // 显示窗口
        popEnterPassword.showAtLocation(UdateOrFindPayActivity.this.findViewById(R.id.layoutContent),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置

        popEnterPassword.setCallback(new PopEnterPassword.Callback() {
            @Override
            public void getpassword(String password) {
                String beforepassword = PreferencesUtil.getString(UdateOrFindPayActivity.this, C.FILE_NAME ,"paypassword");
                if (password.equals(beforepassword)){
                    FirstPassword();
                }else {
                    Toast.makeText(UdateOrFindPayActivity.this, "密码错误，请尝试重试或者找回密码", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    /**
     * 函数名： FirstPassword
     * 创建人： TanXin.
     * 创建日期： 2016/10/13 15:51.
     * 功能描述：第一次调起支付键盘：
     * 参考文档：https://github.com/zuiwuyuan/WeChatPswKeyboard
     */
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

    /**
     * 函数名： SecondPassword
     * 创建人： TanXin.
     * 创建日期： 2016/10/13 15:52.
     * 功能描述：第一次调起支付键盘：
     * 参考文档：https://github.com/zuiwuyuan/WeChatPswKeyboard
     */
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
                    PreferencesUtil.putString(UdateOrFindPayActivity.this, C.FILE_NAME,"paypassword",password);
                    finish();
                    Toast.makeText(UdateOrFindPayActivity.this,"输入完成"+password, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UdateOrFindPayActivity.this,"两个密码不相同，请再次输入", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: //返回
                finish();
                break;
        }
        return true;
    }
}
