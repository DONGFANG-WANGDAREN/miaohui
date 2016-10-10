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
 * Description:收支明细--支出 <br>
 *
 * @auther TX <br>
 * created at 2016/9/8 16:55
 */
public class IncomeChuFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {
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
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#b3b3b3"), ScreenUtil.dip2px(getActivity(), 0.5f), ScreenUtil.dip2px(getActivity(), 0), 0);
        itemDecoration.setDrawLastItem(false);
        recycle.addItemDecoration(itemDecoration);
        recycle.setRefreshingColorResources(R.color.toolbarcolor);
        recycle.setAdapterWithProgress(adapter = new RecyclerArrayAdapter(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new InComeViewHolder(parent);
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
                IncomeEnity income = (IncomeEnity) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), IncomeDetailActivity.class);
                intent.putExtra("income", income);
                startActivity(intent);

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
                adapter.addAll(DataProvider.getIncomechu());
                //sumbit(page);
                // page++;
            }
        }, 1000);
    }

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
                adapter.addAll(DataProvider.getIncomechu());
                //sumbit(page);
                //   page = 1;
            }
        }, 1000);
    }

}
