package com.dingzhi.miaohui.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.ui.fragment.MyFragment;
import com.dingzhi.miaohui.util.glideutil.GlideUtils;

import butterknife.BindView;
import butterknife.OnClick;

 /**
   * explain 提交订单界面
   * created by TanXin.
   * created date 2016/9/21 15:35.
   */
public class SubmitOrderActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.img_sex)
    ImageView imgSex;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_hourly)
    TextView tvHourly;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_context)
    TextView tvContext;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.radio_zhifu)
    RadioGroup radioZhifu;
    @BindView(R.id.btn_sure)
    Button btnSure;

    @Override
    protected int setLayout() {
        return R.layout.activity_submit_order;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("提交支付");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GlideUtils.getInstance().loadRoundImageView(this, MyFragment.MOCK_DATA_URL, imgHead);
        radioZhifu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_zhifubao:
                        Toast.makeText(SubmitOrderActivity.this, "支付宝", Toast.LENGTH_SHORT).show();
                    break;
                    case R.id.radio_qianbao:
                        Toast.makeText(SubmitOrderActivity.this, "钱包", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


    @OnClick(R.id.btn_sure)
    public void onClick() {
        startActivity(new Intent(this, OrderDetailActivity.class));
    }
}
