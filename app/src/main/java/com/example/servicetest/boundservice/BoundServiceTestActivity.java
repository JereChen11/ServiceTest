package com.example.servicetest.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.servicetest.R;

/**
 * @author jere
 */
public class BoundServiceTestActivity extends AppCompatActivity {
    private static final String TAG = "BoundServiceTestActivity";
    private MyBoundService boundService;
    private boolean isBoundService = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service_test);

        Button getRandomBtn = findViewById(R.id.get_number_btn);
        getRandomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoundService) {
                    Toast.makeText(BoundServiceTestActivity.this,
                            "random Number is: " + boundService.getRandomNumber(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        if (isBoundService) {
            unbindService(serviceConnection);
            isBoundService = false;
        }

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.MyBinder binder = (MyBoundService.MyBinder) service;
            boundService = binder.getService();
            isBoundService = true;
            Log.d(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBoundService = false;
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Log.d(TAG, "onBackPressed: execute finish()");
    }
}
