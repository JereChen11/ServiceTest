package com.example.servicetest.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.servicetest.R;

/**
 * @author jere
 */
public class MessengerServiceTestActivity extends AppCompatActivity {
    private static final String TAG = "MessengerServiceTestActivity";
    public static final int MSG_FROM_CLIENT = 1;
    public static final String CLIENT_SEND_MSG_KEY = "CLIENT_SEND_MSG";
    private Messenger mMessenger;
    private boolean isBoundService;
    private Messenger mReplyToMessenger = new Messenger(new ServiceReplyToMessengerHandler());

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //利用服务端返回的 IBinder，新创建一个Messenger对象
            mMessenger = new Messenger(service);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_service_test);

        //点击按钮，客户端向服务端发送信息，并指定了用于接收服务端返回信息的 Messenger 对象
        Button sendMsgBtn = findViewById(R.id.send_message_btn);
        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = MSG_FROM_CLIENT;
                Bundle sendToServiceBundle = new Bundle();
                sendToServiceBundle.putString(CLIENT_SEND_MSG_KEY, "Hi! What's your name!");
                message.setData(sendToServiceBundle);

                //指定用于接收服务端返回信息的 Messenger
                message.replyTo = mReplyToMessenger;
                try {
                    //通过调用 Messenger 中的 send() 方法，将消息发送给服务端
                    mMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //绑定服务
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //如果绑定了服务，则解绑服务
        if (isBoundService) {
            unbindService(serviceConnection);
            isBoundService = false;
        }
    }

    public static class ServiceReplyToMessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MessengerService.MSG_FROM_SERVICE:
                    Bundle serviceReplyBundle = msg.getData();
                    String name = serviceReplyBundle.getString(MessengerService.SERVICE_REPLY_MSG_KEY);
                    Log.e(TAG, "receiver service message info: " + name);
                    break;
                default:
                    break;
            }
        }
    }
}
