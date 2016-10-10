package com.dingzhi.miaohui.ui.activity.myinfo.qianbao;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.adapter.TabPagerAdapter;
import com.dingzhi.miaohui.ui.fragment.qianbao.IncomeAllFragment;
import com.dingzhi.miaohui.ui.fragment.qianbao.IncomeChuFragment;
import com.dingzhi.miaohui.ui.fragment.qianbao.IncomeRuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IncomeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager pager;
    private String[] title = new String[]{"全部", "收入", "支出"};
    private List<Fragment> fragments;
    private TabPagerAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_income;

    }

    @Override
    protected void initView() {
        toolbar.setTitle("现金收支明细");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragments = new ArrayList<>();
        IncomeAllFragment allFragment = new IncomeAllFragment();
        IncomeChuFragment chuFragment = new IncomeChuFragment();
        IncomeRuFragment ruFragment = new IncomeRuFragment();
        fragments.add(allFragment);
        fragments.add(ruFragment);
        fragments.add(chuFragment);
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
