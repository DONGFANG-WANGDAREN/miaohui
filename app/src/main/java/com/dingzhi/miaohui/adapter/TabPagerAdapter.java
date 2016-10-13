package com.dingzhi.miaohui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 文件名：TabPagerAdapter.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:23.
 * 功能描述: TabLayout Adapter
 * 函数/方法说明:
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private List<Fragment> fragments;

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        fragment = (Fragment) super.instantiateItem(container, position);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position%titles.length];
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}
