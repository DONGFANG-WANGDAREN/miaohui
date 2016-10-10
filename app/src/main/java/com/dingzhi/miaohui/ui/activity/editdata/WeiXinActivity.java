package com.dingzhi.miaohui.ui.activity.editdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:绑定微信 <br>
 *
 * @auther TX <br>
 * created at 2016/9/5 10:14
 */
public class WeiXinActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ed_weixin)
    EditText edWeixin;
    private String title;

    @Override
    protected int setLayout() {
        return R.layout.activity_weixin;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String tv = intent.getStringExtra("tv");
        title = intent.getStringExtra("title");
        edWeixin.setText(tv);
        toolbar.setTitle(title);
        edWeixin.setHint("请输入" + title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_complete, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_complete:
                String tv = edWeixin.getText().toString().trim();
                if (tv.isEmpty()) {
                    Toast.makeText(WeiXinActivity.this, "请输入" + title, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("tv", tv);
                    WeiXinActivity.this.setResult(RESULT_OK, intent);
                    WeiXinActivity.this.finish();
                }
                break;

        }
        return true;
    }
}
