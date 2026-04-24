package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.databinding.Day5FragmentDownloadBinding;
import com.example.myapplication.model.DownloadViewModel;

public class Day5DownloadFragment extends Fragment {

    private Day5FragmentDownloadBinding binding;
    private DownloadViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = Day5FragmentDownloadBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DownloadViewModel.class);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        binding.btnDownload.setOnClickListener(v -> viewModel.startDownload());
        binding.btnReset.setOnClickListener(v -> viewModel.resetDownload());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}