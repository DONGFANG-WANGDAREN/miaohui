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
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;


/**
 * 文件名：LikeFragment.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 16:54.
 * 功能描述: 喜欢列表
 * 函数/方法说明:
 */
public class LikeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener{

    private Toolbar toolbar;
    private EasyRecyclerView recycle;
    private RecyclerArrayAdapter adapter;
    private Handler handler = new Handler();
    private int page = 0;
    private boolean hasNetWork;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like, container, false);
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
        toolbar.setTitle("喜欢");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    /**
     * 函数名： initRcycle
     * 创建人： TanXin.
     * 创建日期： 2016/10/13 16:21.
     * 功能描述：初始化EasyRecycleView
     * 参考文档：https://github.com/Jude95/EasyRecyclerView
     * 附加说明：第三方开源库，上拉下拉RecycleView ,initRcycle内方法参考IncomeAllFragment
     */
    private void initRecycle() {
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#b3b3b3"), ScreenUtil.dip2px(getActivity(), 0.5f), ScreenUtil.dip2px(getActivity(), 0), 0);
        itemDecoration.setDrawLastItem(false);
        recycle.addItemDecoration(itemDecoration);
        recycle.setRefreshingColorResources(R.color.toolbarcolor);

        recycle.setAdapterWithProgress(adapter = new RecyclerArrayAdapter(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new LikeViewHolder(parent);
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

    /**
     * 函数名： onLoadMore
     * 创建人： TanXin.
     * 创建日期： 2016/10/13 16:30.
     * 功能描述：加载更多
     */
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
                adapter.addAll(DataProvider.getwozutaList(page));
                //sumbit(page);
                page++;
            }
        }, 2000);
    }

    /**
     * 函数名： onRefresh
     * 创建人： TanXin.
     * 创建日期： 2016/10/13 16:30.
     * 功能描述：下拉刷新
     */
    @Override
    public void onRefresh() {
        hasNetWork = NetUtil.isConnected(getActivity());
        page = 1;
        handler.postDelayed(    new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                //刷新
                if (!hasNetWork) {
                    adapter.pauseMore();
                    return;
                }
                if (adapter.getCount()<7){
                    adapter.stopMore();
                }
                adapter.addAll(DataProvider.getwozutaList(page));
                //sumbit(page);
                page = 1;
            }
        }, 2000);
    }


}
