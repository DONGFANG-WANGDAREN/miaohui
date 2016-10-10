package com.dingzhi.miaohui.ui.activity.editdata;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:修改密码 <br>
 *
 * @auther TX <br>
 * created at 2016/9/5 15:44
 */
public class ModifyActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int setLayout() {
        return R.layout.activity_modify;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("修改密码");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
