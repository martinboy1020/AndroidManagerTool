package com.martinboy.managertool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;

public class WifiReceiver extends BroadcastReceiver {

    private IntentFilter intentFilter;

    public WifiReceiver() {
        if(intentFilter == null) {
            intentFilter = new IntentFilter();
            intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null &&
                intent.getAction() != null &&
                intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {

        }
    }
}
