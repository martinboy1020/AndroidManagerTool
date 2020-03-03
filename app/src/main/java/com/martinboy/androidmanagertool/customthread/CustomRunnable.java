package com.martinboy.androidmanagertool.customthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class CustomRunnable implements Runnable {

    private CallBack callBack;

    public CustomRunnable(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void run() {
        Looper.prepare();
        Handler handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("tag1", "CustomRunnable Handle Message");
                if(callBack != null)
                    callBack.returnValue("I am running for customRunnable");
            }
        };
        handler.sendEmptyMessage(new Message().what = 1);
        Looper.loop();
    }

    public interface CallBack {

        void returnValue(String word);

    }

}
