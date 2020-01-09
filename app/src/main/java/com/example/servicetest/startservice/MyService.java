package com.example.servicetest.startservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * @author jere
 */
public class MyService extends Service {
    private static final String TAG = "MyService";
    public MyService() {
    }

    /**
     * 在call bindService()方法时调用。在此方法的实现中，必须提供一个接口，客户端可以通过返回IBinder使用该接口与服务进行通信。
     * 当你实现绑定时，必须始终实现此方法。但是，如果不允许绑定，则应返回null。
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 在最初创建服务时（在调用onStartCommand（）或onBind（）之前），系统将调用此方法以执行一次性设置过程。
     * 如果服务已经在运行，则不会调用此方法。
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    /**
     * 当另一个组件（例如活动）请求启动服务时，系统通过调用startService()来调用此方法。
     * 执行此方法时，服务将启动并可以无限期在后台运行。
     * 如果执行此操作，则 必须 通过调用stopSelf()或stopService()在服务完成后停止该服务。如果只想提供绑定，则不需要实现此方法。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    Log.d(TAG, "run: i = " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(TAG, "run: 1");
//            }
//        },2000);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(TAG, "run: 2");
//            }
//        },10000);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 当服务不再使用并被销毁时，系统将调用此方法，所以这是服务收到的最后一个呼叫。
     * 您的服务应实现此目的，以清理所有资源，例如线程，注册的侦听器或接收器。
     */
    @Override
    public void onDestroy() {
        stopSelf();
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}
