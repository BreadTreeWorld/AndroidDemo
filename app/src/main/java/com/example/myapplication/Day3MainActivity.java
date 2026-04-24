//package com.example.myapplication;
//
//import android.app.Activity;
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentActivity;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.example.myapplication.adapter.ViewPagerAdapter;
//import com.google.android.material.tabs.TabLayout;
//import com.google.android.material.tabs.TabLayoutMediator;
//public class Day3MainActivity extends AppCompatActivity {
////public class Day3MainActivity extends FragmentActivity {
//
//    private TabLayout tabLayout;
//    private ViewPager2 viewPager;
//    private ViewPagerAdapter viewPagerAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.day3_activity_main);
////        setContentView(R.layout.day3_activity_main);
//        initViews();
//        setupViewPager();
//    }
//
//    private void initViews() {
//        tabLayout = findViewById(R.id.tabLayout);
//        viewPager = findViewById(R.id.viewPager);
//    }
//
//    private void setupViewPager() {
//        viewPagerAdapter = new ViewPagerAdapter(this);
//        viewPager.setAdapter(viewPagerAdapter);
//
//        // 关联TabLayout和ViewPager2
//        new TabLayoutMediator(tabLayout, viewPager,
//                (tab, position) -> {
//                    if (position == 0) {
//                        tab.setText("DOWNLOAD");
//                    } else {
//                        tab.setText("RECYCLER LIST");
//                    }
//                }
//        ).attach();
//    }
//}