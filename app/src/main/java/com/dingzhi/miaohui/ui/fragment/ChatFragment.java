package com.dingzhi.miaohui.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dingzhi.miaohui.R;

/**
 * 文件名：ChatFragment.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 16:35.
 * 功能描述: 聊天
 * 函数/方法说明:
 */
public class ChatFragment extends Fragment {

    private Toolbar toolbar;
    public ChatFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        toolbar  = (Toolbar) view.findViewById(R.id.toolbar);
    }
    private void initData() {
        toolbar.setTitle("消息");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

}
