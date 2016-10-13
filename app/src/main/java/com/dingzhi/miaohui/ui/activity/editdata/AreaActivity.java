package com.dingzhi.miaohui.ui.activity.editdata;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dingzhi.miaohui.BaseActivity;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.adapter.SelectAdapter;
import com.dingzhi.miaohui.util.C;
import com.dingzhi.miaohui.widget.DividerItemDecoration;
import com.dingzhi.miaohui.widget.DragSelectRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文件名：AreaActivity.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 15:20.
 * 功能描述: 选择家乡页面
 * 函数/方法说明:
 * 参考资料：https://github.com/afollestad/drag-select-recyclerview  多选recycleview
 */
public class AreaActivity extends BaseActivity implements SelectAdapter.ClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.list)
    DragSelectRecyclerView recycleview;
    private final static String[] ALPHABET = {"北京", "上海", "天津", "重庆", "安徽", "福建", "甘肃",
            "广东", "贵州", "河北", "黑龙江", "河南", "湖北", "湖南", "吉林", "江西", "辽宁", "山东", "陕西", "山西", "四川"
            , "云南", "浙江", "青海", "海南", "台湾", "广西", "内蒙古", "宁夏", "新疆", "香港"};
    private SelectAdapter mAdapter;
    private List<String> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter.restoreInstanceState(savedInstanceState);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_industry;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("家乡");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitle.setText("创建自己的家乡");
        // 安装适配器和回调
        list = new ArrayList<>();
        Collections.addAll(list, ALPHABET);
        mAdapter = new SelectAdapter(this, list);
        // 设置RecyclerView
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recycleview.setAdapter(mAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mAdapter.saveInstanceState(outState);

    }

    @Override
    public void onClick(int index) {
        Intent intent = new Intent();
        intent.putExtra("tv", mAdapter.getItem(index));
        AreaActivity.this.setResult(RESULT_OK, intent);
        AreaActivity.this.finish();
    }


    @Override
    public void onBackPressed() {
        if (mAdapter.getSelectedCount() > 0)
            mAdapter.clearSelected();
        else super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
//                StringBuilder sb = new StringBuilder();
//                int traverse = 0;
//                for (Integer index : mAdapter.getSelectedIndices()) {
//                    if (traverse > 0) sb.append(", ");
//                    sb.append(mAdapter.getItem(index));
//                    traverse++;
//                }
//                Toast.makeText(this,
//                        mAdapter.getSelectedCount()+"___"+sb.toString()+"``",
//                        Toast.LENGTH_LONG).show();
//                mAdapter.clearSelected();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case C.REQUEST_CODE_WEIXIN:
                if (resultCode == RESULT_OK) {
                    list.add(data.getExtras().getString("tv"));
                }

        }
    }

    @OnClick(R.id.tv_title)
    public void onClick() {
        Intent intent = new Intent(this, WeiXinActivity.class);
        intent.putExtra("tv", "");
        intent.putExtra("title", "家乡");
        startActivityForResult(intent, C.REQUEST_CODE_WEIXIN);
    }
}
