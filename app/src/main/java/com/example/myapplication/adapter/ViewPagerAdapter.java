//package com.example.myapplication.adapter;
//
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentActivity;
//import androidx.viewpager2.adapter.FragmentStateAdapter;
//
////import com.example.myapplication.fragment.DownloadFragment;
//import com.example.myapplication.fragment.Day4DownloadFragment;
//import com.example.myapplication.fragment.ListFragment;
//
//public class ViewPagerAdapter extends FragmentStateAdapter {
//
//    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
//        super(fragmentActivity);
//    }
//
//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        if (position == 0) {
//            return new Day4DownloadFragment();
//        } else {
//            return new ListFragment();
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return 2;  // 两个标签页
//    }
//}
