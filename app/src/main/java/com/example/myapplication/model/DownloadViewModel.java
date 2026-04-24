package com.example.myapplication.model;

import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DownloadViewModel extends ViewModel {

    private final MutableLiveData<Integer> progressLiveData = new MutableLiveData<>(0);
    private final MutableLiveData<Boolean> imageVisibleLiveData = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> isDownloadingLiveData = new MutableLiveData<>(false);

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable downloadRunnable;

    public LiveData<Integer> getProgress() {
        return progressLiveData;
    }

    public LiveData<Boolean> getImageVisible() {
        return imageVisibleLiveData;
    }

    public LiveData<Boolean> getIsDownloading() {
        return isDownloadingLiveData;
    }

    public boolean isDownloading() {
        return Boolean.TRUE.equals(isDownloadingLiveData.getValue());
    }

    public void startDownload() {
        if (isDownloading()) {
            return;
        }

        progressLiveData.setValue(0);
        imageVisibleLiveData.setValue(false);
        isDownloadingLiveData.setValue(true);

        downloadRunnable = new Runnable() {
            @Override
            public void run() {
                Integer currentProgress = progressLiveData.getValue();
                if (currentProgress == null) currentProgress = 0;

                if (currentProgress < 100) {
                    currentProgress += 10;
                    progressLiveData.setValue(currentProgress);
                    handler.postDelayed(this, 1000);
                } else {
                    isDownloadingLiveData.setValue(false);
                    imageVisibleLiveData.setValue(true);
                }
            }
        };

        handler.post(downloadRunnable);
    }

    public void resetDownload() {
        if (downloadRunnable != null) {
            handler.removeCallbacks(downloadRunnable);
        }
        progressLiveData.setValue(0);
        imageVisibleLiveData.setValue(false);
        isDownloadingLiveData.setValue(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (downloadRunnable != null) {
            handler.removeCallbacks(downloadRunnable);
        }
    }
}