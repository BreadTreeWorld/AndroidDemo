package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.myapplication.adapter.Day5ListAdapter;
import com.example.myapplication.databinding.Day5ListFragmentBinding;
import com.example.myapplication.model.ListViewModel;
import java.util.List;

import android.widget.Toast;
import com.example.myapplication.model.Post;

public class Day5ListFragment extends Fragment {

    private Day5ListFragmentBinding binding;
    private ListViewModel viewModel;
    private Day5ListAdapter adapter;
    private boolean hasLoaded = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // 使用 DataBinding 加载布局
        binding = Day5ListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. 初始化 ViewModel
        viewModel = new ViewModelProvider(this).get(ListViewModel.class);

        // 2. 初始化 RecyclerView
        setupRecyclerView();

        // 3. 设置 DataBinding
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        // 4. 设置下拉刷新监听
        setupSwipeRefresh();

        // 5. 观察数据变化
        observeData();

        // 6. 观察加载状态
        observeLoadingState();

        // 7. 观察错误信息
        observeError();

        // 8. 首次进入时加载数据
        loadDataIfNeeded();
    }

    /**
     * 初始化 RecyclerView
     */
    private void setupRecyclerView() {
        adapter = new Day5ListAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    /**
     * 设置下拉刷新监听
     */
    private void setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            // 下拉刷新，重新加载数据
            viewModel.refresh();
        });
    }

    /**
     * 观察文章列表数据变化
     */
    private void observeData() {
        viewModel.getArticles().observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                if (posts != null && !posts.isEmpty()) {
                    adapter.submitList(posts);
                    hasLoaded = true;
                    android.util.Log.d("ListFragment", "数据加载成功，共 " + posts.size() + " 条");
                } else if (posts != null && posts.isEmpty()) {
                    android.util.Log.d("ListFragment", "数据为空");
                    showEmptyState();
                }
            }
        });
    }

    /**
     * 观察加载状态（显示/隐藏加载动画）
     */
    private void observeLoadingState() {
        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null && isLoading) {
                android.util.Log.d("ListFragment", "正在加载数据...");
                // 可以在这里显示加载进度条
            }
        });
    }

    /**
     * 观察错误信息
     */
    private void observeError() {
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null && !error.isEmpty()) {
                Toast.makeText(getContext(), "加载失败: " + error, Toast.LENGTH_SHORT).show();
                android.util.Log.e("ListFragment", "错误: " + error);
            }
        });
    }

    /**
     * 首次进入时加载数据（只加载一次）
     */
    private void loadDataIfNeeded() {
        if (!hasLoaded) {
            android.util.Log.d("ListFragment", "首次进入，开始加载数据");
            viewModel.loadPosts();
        }
    }

    /**
     * 显示空状态（列表为空时）
     */
    private void showEmptyState() {
        // 可以在这里显示一个空视图提示用户
        Toast.makeText(getContext(), "暂无数据，请稍后重试", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 避免内存泄漏
        binding = null;
    }
}
