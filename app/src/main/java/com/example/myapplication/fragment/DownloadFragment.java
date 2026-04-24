package com.example.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class DownloadFragment extends Fragment {

    private ImageView ivPreview;
    private ProgressBar progressBar;
    private TextView tvProgress;
    private Button btnDownload;
    private Button btnReset;

    private Handler handler = new Handler();
    private Runnable downloadRunnable;
    private int progress = 0;
    private boolean isDownloading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        initViews(view);
        setupListeners();
        return view;
    }

    private void initViews(View view) {
        ivPreview = view.findViewById(R.id.iv_preview);
        progressBar = view.findViewById(R.id.progressBar);
        tvProgress = view.findViewById(R.id.tv_progress);
        btnDownload = view.findViewById(R.id.btn_download);
        btnReset = view.findViewById(R.id.btn_reset);

        // 初始状态：图片不可见
        ivPreview.setVisibility(View.INVISIBLE);
        progressBar.setProgress(0);
        tvProgress.setText("0%");
    }

    private void setupListeners() {
        btnDownload.setOnClickListener(v -> startDownload());
        btnReset.setOnClickListener(v -> resetDownload());
    }

    private void startDownload() {
        if (isDownloading) return;

        isDownloading = true;
        progress = 0;
        progressBar.setProgress(0);
        tvProgress.setText("0%");
        ivPreview.setVisibility(View.INVISIBLE);

        downloadRunnable = new Runnable() {
            @Override
            public void run() {
                if (progress < 100) {
                    progress += 10;
                    progressBar.setProgress(progress);
                    tvProgress.setText(progress + "%");
                    handler.postDelayed(this, 1000);  // 每秒执行一次
                } else {
                    // 下载完成
                    isDownloading = false;
                    ivPreview.setVisibility(View.VISIBLE);
                    tvProgress.setText("100%");
                }
            }
        };

        handler.post(downloadRunnable);
    }

    private void resetDownload() {
        // 停止下载
        if (downloadRunnable != null) {
            handler.removeCallbacks(downloadRunnable);
        }

        isDownloading = false;
        progress = 0;
        progressBar.setProgress(0);
        tvProgress.setText("0%");
        ivPreview.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (downloadRunnable != null) {
            handler.removeCallbacks(downloadRunnable);
        }
    }
}
