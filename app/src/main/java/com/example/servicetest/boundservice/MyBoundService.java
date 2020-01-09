package com.example.servicetest.boundservice;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

/**
 * @author jere
 */
public class MyBoundService extends Service {
    private static final String TAG = "MyBoundService";
    private final IBinder mBinder = new MyBinder();
    private final Random mGenerator = new Random();

    public MyBoundService() {
    }

    /**
     * 在call bindService()方法时调用。在此方法的实现中，必须提供一个接口，客户端可以通过返回IBinder使用该接口与服务进行通信。
     * 当你实现绑定时，必须始终实现此方法。但是，如果不允许绑定，则应返回null。
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }

    class MyBinder extends Binder {
        MyBoundService getService() {
            //返回MyBoundService的实例
            return MyBoundService.this;
        }
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        Log.d(TAG, "unbindService: ");
        super.unbindService(conn);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }


}
