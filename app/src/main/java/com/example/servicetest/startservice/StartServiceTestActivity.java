package com.example.servicetest.startservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.servicetest.R;

/**
 * @author jere
 */
public class StartServiceTestActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "StartServiceTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service_test);

        Button startBtn = findViewById(R.id.start_service_btn);
        startBtn.setOnClickListener(this);
        Button stopBtn = findViewById(R.id.stop_service_btn);
        stopBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service_btn:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.stop_service_btn:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Log.d(TAG, "onBackPressed: execute finish()");
    }
}
