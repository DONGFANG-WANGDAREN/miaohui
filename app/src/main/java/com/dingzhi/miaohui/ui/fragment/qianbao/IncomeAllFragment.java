package com.dingzhi.miaohui.ui.fragment.qianbao;


import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.dingzhi.miaohui.BaseFragment;
import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.enity.IncomeEnity;
import com.dingzhi.miaohui.nullss.DataProvider;
import com.dingzhi.miaohui.ui.activity.myinfo.qianbao.IncomeDetailActivity;
import com.dingzhi.miaohui.util.NetUtil;
import com.dingzhi.miaohui.util.ScreenUtil;
import com.dingzhi.miaohui.viewholder.InComeViewHolder;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import butterknife.BindView;

/**
 * 文件名：IncomeAllFragment.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 16:19.
 * 功能描述:收支明细——全部
 * 函数/方法说明:
 */
public class IncomeAllFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    @BindView(R.id.recycle)
    EasyRecyclerView recycle;
    private RecyclerArrayAdapter adapter;
    private Handler handler = new Handler();
    private int page = 0;
    private boolean hasNetWork;


    @Override
    public int getContentViewId() {
        return R.layout.fragment_income_all;
    }

    @Override
    protected void initView() {
        initRcycle();
    }

     /**
       * 函数名： initRcycle
       * 创建人： TanXin.
       * 创建日期： 2016/10/13 16:21.
       * 功能描述：初始化EasyRecycleView
       * 参考文档：https://github.com/Jude95/EasyRecyclerView
       * 附加说明：第三方开源库，上拉下拉RecycleView ,initRcycle内方法参考IncomeAllFragment
       */
    public void initRcycle(){
        //设置EasyRecycleView的模式：线性，网格，瀑布流
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置EasyRecycleView的分割线
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#b3b3b3"), ScreenUtil.dip2px(getActivity(), 0.5f), ScreenUtil.dip2px(getActivity(), 0), 0);
        itemDecoration.setDrawLastItem(false);
        recycle.addItemDecoration(itemDecoration);
        //设置EasyRecycleView下拉进度条的颜色
        recycle.setRefreshingColorResources(R.color.toolbarcolor);
        //设置EasyRecycleView的adapter
        recycle.setAdapterWithProgress(adapter = new RecyclerArrayAdapter(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new InComeViewHolder(parent);
            }
        });
        //设置更多
        adapter.setMore(R.layout.view_more, this);
        //设置没有更多信息
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
        //设置错误信息
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
        //长按点击事件
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                adapter.remove(position);
                return true;
            }
        });
        //点击事件
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                IncomeEnity income = (IncomeEnity) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), IncomeDetailActivity.class);
                intent.putExtra("income", income);
                startActivity(intent);

            }
        });
        recycle.setRefreshListener(this);
        onRefresh(); //开启刷新
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
                adapter.addAll(DataProvider.getIncomeAll());
                //sumbit(page);
                // page++;
            }
        }, 1000);
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
        // page = 1;
        handler.postDelayed(new Runnable() {
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
                adapter.addAll(DataProvider.getIncomeAll());
                //sumbit(page);
                //   page = 1;
            }
        }, 1000);
    }

}
