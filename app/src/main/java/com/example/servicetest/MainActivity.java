package com.example.servicetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.servicetest.boundservice.BoundServiceTestActivity;
import com.example.servicetest.startservice.StartServiceTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button testStartServiceBtn = findViewById(R.id.test_start_service_btn);
        testStartServiceBtn.setOnClickListener(this);
        Button testBoundServiceBtn = findViewById(R.id.test_bound_service_btn);
        testBoundServiceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_start_service_btn:
                Intent testStartServiceIntent = new Intent(this, StartServiceTestActivity.class);
                startActivity(testStartServiceIntent);
                break;
            case R.id.test_bound_service_btn:
                Intent testBoundServiceIntent = new Intent(this, BoundServiceTestActivity.class);
                startActivity(testBoundServiceIntent);
                break;
            default:
                break;
        }
    }

}
