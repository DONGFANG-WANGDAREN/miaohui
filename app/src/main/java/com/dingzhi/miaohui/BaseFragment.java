package com.dingzhi.miaohui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by SRDZ on 2016/9/18.
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;
    protected View rootView;
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getContentViewId(),container,false);
        unbinder = ButterKnife.bind(this,rootView);
        this.context = getActivity();
        initView();
        return rootView;
    }

    public abstract int getContentViewId();
    protected abstract void initView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
