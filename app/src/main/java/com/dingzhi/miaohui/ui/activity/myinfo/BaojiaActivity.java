package com.dingzhi.miaohui.ui.activity.myinfo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.adapter.TabPagerAdapter;
import com.dingzhi.miaohui.ui.fragment.MybaotaFragment;
import com.dingzhi.miaohui.ui.fragment.TabaowoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaojiaActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager pager;
    private String[] title = new String[]{"我对TA报价", "TA对我报价"};
    private List<Fragment> fragments;
    private TabPagerAdapter adapter;
    private MybaotaFragment mybaotaFragment;
    private TabaowoFragment tabaowoFragment;

    @Override
    protected int setLayout() {
        return R.layout.activity_baojia;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("报价");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragments = new ArrayList<>();
        mybaotaFragment = new MybaotaFragment();
        tabaowoFragment = new TabaowoFragment();
        fragments.add(mybaotaFragment);
        fragments.add(tabaowoFragment);
        adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.setTitles(title);
        adapter.setFragments(fragments);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
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
