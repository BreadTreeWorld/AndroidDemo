//package com.example.myapplication;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.TextView;
//
//import com.example.myapplication.handler.SearchHandler;
//
////Day 1
//public class MainActivity extends Activity {
//
//    private TextView tvFlightDataSize;
//    private SearchHandler searchHandler;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        TextView textView = new TextView(this);
//        textView.setTextSize(24);
//        textView.setPadding(50, 50, 50, 50);
//        textView.setGravity(android.view.Gravity.CENTER);
//
//        SearchHandler searchHandler = new SearchHandler();
//        searchHandler.initData();
//        int flightDataSize = searchHandler.searchFlight();
//
//        textView.setText("flight data size：" + flightDataSize);
//        setContentView(textView);
//
//        Log.d("MainActivity", "flight data size：" + flightDataSize);
//    }
//}
