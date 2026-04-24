package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.fragment.Day5DownloadFragment;
import com.example.myapplication.fragment.Day5ListFragment;

public class Day5ViewPagerAdapter extends FragmentStateAdapter {

    public Day5ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new Day5DownloadFragment();  // 下载页
        } else {
            return new Day5ListFragment();      // 列表页
        }
    }

    @Override
    public int getItemCount() {
        return 2;  // 两个标签页
    }
}
