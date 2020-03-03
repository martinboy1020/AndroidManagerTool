package com.martinboy.androidmanagertool.customthread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

public class CustomThread extends Thread {

    @Override
    public void run() {
        super.run();
//        Looper.prepare();
//        Handler handler = new Handler(Looper.myLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                Log.d("tag1", "CustomThread Handle Message");
//            }
//        };
//        handler.sendEmptyMessage(new Message().what = 1);
//        Looper.loop();

        HandlerThread mHandlerThread = new HandlerThread("handlerThread");
        mHandlerThread.start();

        Handler handler = new Handler(mHandlerThread.getLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("tag1", "CustomThread Handle Message");
            }
        };

        handler.sendEmptyMessage(new Message().what = 1);

    }
}
