package com.dingzhi.miaohui.ui.activity.myinfo.qianbao;

import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
 * 文件名：WithdrawActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 15:58.
 * 功能描述: 提现
 * 函数/方法说明:
 */
public class WithdrawActivity extends BaseActivity  {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_alipayAccount)
    TextView tvAlipayAccount;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_money)
    EditText edMoney;
    @BindView(R.id.btn_sure)
    Button btnSure;
    @BindView(R.id.layoutContent)
    LinearLayout layoutContent;

    @Override
    protected int setLayout() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("提现");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //返回
                finish();
                break;
        }
        return true;
    }

    /**
     * 函数名： FirstPassword
     * 创建人： TanXin.
     * 创建日期： 2016/10/13 15:51.
     * 功能描述：第一次调起支付键盘：
     * 参考文档：https://github.com/zuiwuyuan/WeChatPswKeyboard
     */
    private void FirstPassword(String money) {
        //第一次输入的密码
        PopEnterPassword popEnterPassword = new PopEnterPassword(this, "请输入支付密码", "零钱提现", "￥" + money);

        // 显示窗口
        popEnterPassword.showAtLocation(this.findViewById(R.id.layoutContent),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置

        popEnterPassword.setCallback(new PopEnterPassword.Callback() {
            @Override
            public void getpassword(String password) {
                String beforepassword = PreferencesUtil.getString(WithdrawActivity.this, C.FILE_NAME, "paypassword");
                if (password.equals(beforepassword)) {
                    Toast.makeText(WithdrawActivity.this, "sure", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WithdrawActivity.this, "false", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @OnClick(R.id.btn_sure)
    public void onClick() {
        String name = edName.getText().toString().trim();
        String money = edMoney.getText().toString().trim();
        if (name.equals(PreferencesUtil.getString(this, C.FILE_NAME, "alpayname"))) {
            FirstPassword(money);
        } else if (name.isEmpty()) {
            Toast.makeText(WithdrawActivity.this, "收款人姓名不能为空", Toast.LENGTH_SHORT).show();
        } else if (money.isEmpty()) {
            Toast.makeText(WithdrawActivity.this, "提现金额不能为空", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(WithdrawActivity.this, "提现人姓名与绑定的支付宝账号不相同，请重试", Toast.LENGTH_SHORT).show();
        }
    }
}
