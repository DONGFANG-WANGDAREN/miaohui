package com.dingzhi.miaohui.ui.activity.myinfo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClearCacheActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int setLayout() {
        return R.layout.activity_clear_cache;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("清理缓存");
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
