package com.example.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapplication.service.MyService;


public class Day2MainActivity extends Activity {
    private static final String TAG = "Day2MainActivity";
    private EditText etArrival;
    private Button btnGoToSummary;
    private boolean isReShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day2_activity_main);
        Log.d(TAG, "onCreate: Day2MainActivity Created");
        etArrival = findViewById(R.id.et_arrival);
        btnGoToSummary = findViewById(R.id.btn_go_to_summary);

        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);

        btnGoToSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String arrival = etArrival.getText().toString().trim();
                if (arrival.isEmpty()) {
                    Toast.makeText(Day2MainActivity.this, "请输入arrival", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Day2MainActivity.this, SummaryActivity.class);
                intent.putExtra("arrival", arrival);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "reShow");
        isReShow = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Day2MainActivity destroyed");
    }
}
