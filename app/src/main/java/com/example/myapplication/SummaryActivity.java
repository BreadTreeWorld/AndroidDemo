package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends Activity {
    private TextView tvArrivalInfo;
    private Button btnGoToHome;
    private String arrival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tvArrivalInfo = findViewById(R.id.tv_arrival_info);
        btnGoToHome = findViewById(R.id.btn_go_to_home);

        arrival = getIntent().getStringExtra("arrival");

        tvArrivalInfo.setText("Arrival: " + arrival);

        btnGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummaryActivity.this, Day2MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
