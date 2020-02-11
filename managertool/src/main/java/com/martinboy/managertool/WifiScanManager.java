package com.martinboy.managertool;

import android.content.Context;
import android.net.wifi.WifiManager;

public class WifiScanManager {

    private static WifiScanManager INSTANCE = null;
    private Context context;
    private WifiManager wifiManager;

    private WifiScanManager(Context context) {
        this.context = context;
        if(wifiManager == null) {
            wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        }
    }

    public static synchronized WifiScanManager getInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = new WifiScanManager(context);
        }

        return INSTANCE;

    }

    public void scanWifi() {

        if(CheckConnectStatusManager.checkNetWorkConnect(context)) {

            if(CheckConnectStatusManager.checkConnectType(context) == CheckConnectStatusManager.NetWorkType.WIFiNetWork) {

            } else {

            }

        }

    }



}
