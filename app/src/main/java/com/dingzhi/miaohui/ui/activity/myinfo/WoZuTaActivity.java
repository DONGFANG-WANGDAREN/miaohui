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

/**
 * 文件名：WoZuTaActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 16:12.
 * 功能描述: 我租了TA
 * 函数/方法说明:
 */
public class WoZuTaActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager pager;
    private String[] title = new String[]{"我租了谁", "待服务", "已完成"};
    private List<Fragment> fragments;
    private TabPagerAdapter adapter;


    @Override
    protected int setLayout() {
        return R.layout.activity_wozuta;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("我租了TA");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragments = new ArrayList<>();
        MybaotaFragment mybaotaFragment = new MybaotaFragment();
        MybaotaFragment mybaotaFragment2 = new MybaotaFragment();
        TabaowoFragment tabaowoFragment = new TabaowoFragment();
        fragments.add(mybaotaFragment);
        fragments.add(tabaowoFragment);
        fragments.add(mybaotaFragment2);
        adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.setTitles(title);
        adapter.setFragments(fragments);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
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

}
