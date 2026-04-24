//package com.example.myapplication.fragment;
//
//// DownloadFragment.java
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//import com.example.myapplication.databinding.Day4FragmentDownloadBinding; // 注意：这个类是自动生成的
//import com.example.myapplication.model.DownloadViewModel;
//
//public class Day4DownloadFragment extends Fragment {
//
//    private Day4FragmentDownloadBinding binding;
//    private DownloadViewModel viewModel;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        // 使用 DataBindingUtil 加载布局
//        binding = Day4FragmentDownloadBinding.inflate(inflater, container, false);
//        // 获取 ViewModel 实例
//        viewModel = new ViewModelProvider(this).get(DownloadViewModel.class);
//        // 将 viewModel 绑定到布局文件
//        binding.setViewModel(viewModel);
//        // 让这个 Fragment 观察 LiveData 的生命周期
//        binding.setLifecycleOwner(getViewLifecycleOwner());
//
//        return binding.getRoot();
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        // 设置按钮点击事件
//        binding.btnDownload.setOnClickListener(v -> viewModel.startDownload());
//        binding.btnReset.setOnClickListener(v -> viewModel.resetDownload());
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null; // 避免内存泄漏
//    }
//}
