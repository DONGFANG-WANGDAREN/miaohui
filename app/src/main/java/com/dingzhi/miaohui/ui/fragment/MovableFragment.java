package com.dingzhi.miaohui.ui.fragment;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.nullss.DataProvider;
import com.dingzhi.miaohui.util.NetUtil;
import com.dingzhi.miaohui.util.ScreenUtil;
import com.dingzhi.miaohui.viewholder.LikeViewHolder;
import com.dingzhi.miaohui.viewholder.MovableViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;


public class MovableFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener{

    private Toolbar toolbar;
    private EasyRecyclerView recycle;
    private RecyclerArrayAdapter adapter;
    private Handler handler = new Handler();
    private boolean hasNetWork;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movable, container, false);
        initView(view);
        initRecycle();
        initData();
        return view;
    }
    private void initView(View view) {
        toolbar  = (Toolbar) view.findViewById(R.id.toolbar);
        recycle = (EasyRecyclerView) view.findViewById(R.id.recycle);
    }
    private void initData() {
        toolbar.setTitle("活动");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void initRecycle() {
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));

        recycle.setRefreshingColorResources(R.color.toolbarcolor);

        recycle.setAdapterWithProgress(adapter = new RecyclerArrayAdapter(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new MovableViewHolder(parent);
            }
        });
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
                adapter.resumeMore();
            }

            @Override
            public void onNoMoreClick() {
                adapter.resumeMore();
            }
        });


        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
            }
        });

        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                adapter.remove(position);
                return true;
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        recycle.setRefreshListener(this);
        onRefresh();

    }



    @Override
    public void onLoadMore() {
        hasNetWork = NetUtil.isConnected(getActivity());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //刷新
                if (!hasNetWork) {
                    adapter.pauseMore();
                    return;
                }
                adapter.addAll(DataProvider.getmovable());

            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        hasNetWork = NetUtil.isConnected(getActivity());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                //刷新
                if (!hasNetWork) {
                    adapter.pauseMore();
                    return;
                }
                if (adapter.getCount()<4){
                    adapter.stopMore();
                }
                adapter.addAll(DataProvider.getmovable());

            }
        }, 2000);
    }
}
