package com.example.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CustomLoadingDialog;
import com.example.myapplication.adapter.ListAdapter;
import com.example.myapplication.model.ListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListAdapter adapter;
    private List<ListItem> dataList;
    private CustomLoadingDialog loadingDialog;
    private Random random = new Random();
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initViews(view);
        setupRecyclerView();
        setupSwipeRefresh();
        loadInitialData();
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        loadingDialog = new CustomLoadingDialog(getContext());
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataList = new ArrayList<>();
        adapter = new ListAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }

    private void setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // 显示自定义Loading Dialog
            loadingDialog.show();

            // 模拟网络请求，延迟4秒
            handler.postDelayed(() -> {
                refreshData();
                loadingDialog.dismiss();
                swipeRefreshLayout.setRefreshing(false);
            }, 4000);
        });
    }

    private void loadInitialData() {
        // 初始数据：Header + 5个Content
        dataList.clear();
        dataList.add(new ListItem(ListItem.TYPE_HEADER, "Header"));
        for (int i = 0; i < 5; i++) {
            dataList.add(new ListItem(ListItem.TYPE_CONTENT, "item: " + i));
        }
        adapter.notifyDataSetChanged();
    }

    private void refreshData() {
        // 用随机数更新列表内容
        dataList.clear();
        dataList.add(new ListItem(ListItem.TYPE_HEADER, "Header"));

        for (int i = 0; i < 5; i++) {
            int randomNum = random.nextInt(100);
            dataList.add(new ListItem(ListItem.TYPE_CONTENT,
                    "refreshed item, the random num is: " + randomNum));
        }

        adapter.notifyDataSetChanged();
    }
}