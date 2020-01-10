package com.example.servicetest.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * @author jere
 */
public class MessengerService extends Service {
    private static final String TAG = "MessengerService";
    public static final int MSG_FROM_CLIENT = 1;

    private Messenger mMessenger = new Messenger(new MyHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    public static class MyHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msgFromClient) {
            super.handleMessage(msgFromClient);
//            Message msgToClient = Message.obtain(msgFromClient);
            Log.e(TAG, "handleMessage msg.what: " + msgFromClient.what);
            Log.e(TAG, "handleMessage msg.arg1: " + msgFromClient.arg1);
            Log.e(TAG, "handleMessage msg.arg2: " + msgFromClient.arg2);
//            switch (msgFromClient.what) {
//                case MSG_FROM_CLIENT:
//                    msgToClient.what = MSG_FROM_CLIENT;
//                    String testString = "jereTest";
//                    testString.equals("jereTest");
//                    try {
//                        //模拟耗时
//                        Thread.sleep(2000);
//                        msgToClient.arg2 = msgFromClient.arg1 + msgFromClient.arg2;
//                        msgFromClient.replyTo.send(msgToClient);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                default:
//                    break;
//            }


        }
    }

}
