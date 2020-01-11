package com.example.servicetest.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
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
    public static final int MSG_FROM_SERVICE = 2;
    public static final String SERVICE_REPLY_MSG_KEY = "SERVICE_REPLY_MSG";
    private Messenger mMessenger = new Messenger(new MyHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    public static class MyHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msgFromClient) {
            super.handleMessage(msgFromClient);
            Bundle sendByClientBundle = msgFromClient.getData();
            String clientMsg = sendByClientBundle.getString(MessengerServiceTestActivity.CLIENT_SEND_MSG_KEY);
            Log.e(TAG, "client send message: " + clientMsg);
            switch (msgFromClient.what) {
                case MessengerServiceTestActivity.MSG_FROM_CLIENT:
                    try {
                        //模拟耗时4秒，服务端回应信息给客户端
                        Thread.sleep(4000);
                        Message replyClientMsg = new Message();
                        replyClientMsg.what = MSG_FROM_SERVICE;
                        Bundle bundle = new Bundle();
                        bundle.putString(SERVICE_REPLY_MSG_KEY, "My Name is Jere");
                        replyClientMsg.setData(bundle);
                        //回应消息给客户端
                        msgFromClient.replyTo.send(replyClientMsg);
                    } catch (InterruptedException | RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
