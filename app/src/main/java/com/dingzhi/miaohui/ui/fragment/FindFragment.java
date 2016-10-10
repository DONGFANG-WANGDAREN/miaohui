package com.dingzhi.miaohui.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dingzhi.miaohui.R;
import com.dingzhi.miaohui.adapter.FindAdapter;
import com.dingzhi.miaohui.enity.FindEnity;
import com.dingzhi.miaohui.nullss.DataProvider;
import com.dingzhi.miaohui.ui.activity.ConfirmOrderActivity;
import com.dingzhi.miaohui.ui.activity.PersonDetailActivity;
import com.dingzhi.miaohui.widget.FilterDialog;
import com.dingzhi.miaohui.widget.flingswipe.SwipeFlingAdapterView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame)
    SwipeFlingAdapterView frame;
    @BindView(R.id.main_left)
    ImageView mainLeft;
    @BindView(R.id.main_zuta)
    ImageView mainZuta;
    @BindView(R.id.main_right)
    ImageView mainRight;
    private List al;
    private FindAdapter adapter;
    private int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        toolbar.setTitle("喵会");
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        al = DataProvider.getfindlist(1);
        adapter = new FindAdapter(getActivity(), al);
        frame.setAdapter(adapter);
        frame.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                al.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Toast.makeText(getActivity(), "不喜欢", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(getActivity(), "喜欢", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                al.add(new FindEnity("循环测试", i, "http://e.hiphotos.baidu.com/image/h%3D360/sign=58179dd62a2eb938f36d7cf4e56385fe/d0c8a786c9177f3e6bf9623a75cf3bc79f3d5633.jpg"));
                adapter.notifyDataSetChanged();
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                try {
                    View view = frame.getSelectedView();
                    view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                    view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        frame.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                startActivity(new Intent(getActivity(), PersonDetailActivity.class));
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_filter, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_complete://筛选
                final FilterDialog dialog = new FilterDialog(getActivity(), R.style.filterstyle);
                dialog.setOnButtonClick(new FilterDialog.onButtonClick() {
                    @Override
                    public void OnSure(int sex, int age, int range) {
                        Toast.makeText(getActivity(), sex + "&&" + age + "&&" + range, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                    @Override
                    public void OnCancel() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
        return true;
    }

    @OnClick({R.id.main_left, R.id.main_zuta, R.id.main_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_left:
                frame.getTopCardListener().selectLeft();
                break;
            case R.id.main_zuta:
                startActivity(new Intent(getActivity(),ConfirmOrderActivity.class));
                break;
            case R.id.main_right:
                frame.getTopCardListener().selectRight();
                break;
        }
    }
}
