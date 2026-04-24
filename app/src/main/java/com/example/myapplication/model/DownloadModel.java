package com.example.myapplication.model;

public class DownloadModel {
    private int progress;
    private boolean isDownloading;

    public DownloadModel() {
        this.progress = 0;
        this.isDownloading = false;
    }

    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress = progress; }
    public boolean isDownloading() { return isDownloading; }
    public void setDownloading(boolean downloading) { isDownloading = downloading; }
}
