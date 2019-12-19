package com.martinboy.managertool;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.util.Log;

public class MobileConnectStateReceiver extends BroadcastReceiver {

    private static String TAG = MobileConnectStateReceiver.class.getSimpleName();

    private LocationManager locationManager;

    private Context context;
    private boolean gps_enabled = false;
    private boolean network_enabled = false;
    private StatusListener listener;
    private IntentFilter intentFilter;

    public MobileConnectStateReceiver(Context context, MobileConnectStateReceiver.StatusListener statusListener) {
        this.context = context;
        this.listener = statusListener;
        if(intentFilter == null) {
            intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            intentFilter.addAction(LocationManager.PROVIDERS_CHANGED_ACTION);
            intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "MobileConnectStateReceiver onReceive");

        final String action = intent.getAction();

        if (action != null) {

            if (listener == null)
                return;

            if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if(CheckConnectStatusManager.checkConnectType(context) == CheckConnectStatusManager.NetWorkType.MobileNetWork) {
                    Log.d(TAG, "network is opened");
                    listener.networkStatusListener(true);
                } else if (CheckConnectStatusManager.checkConnectType(context) == CheckConnectStatusManager.NetWorkType.WIFiNetWork) {
                    Log.d(TAG, "network is opened");
                    listener.networkStatusListener(true);
                } else {
                    Log.d(TAG, "network is not opened");
                    listener.networkStatusListener(false);
                }
            }

            if (action.equals(LocationManager.PROVIDERS_CHANGED_ACTION)) {
                if (locationManager == null) {
                    locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                }

                if (locationManager == null)
                    return;

                try {
                    gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (gps_enabled || network_enabled) {
                    Log.d(TAG, "gps location or network location is opened");
                    listener.gpsStatusListener(true);
                } else {
                    Log.d(TAG, "location is not opened");
                    listener.gpsStatusListener(false);
                }

            }

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "bluetooth is not opened");
                        if (listener != null) {
                            listener.blueToothStatusListener(false);
                        }
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "bluetooth is opened");
                        if (listener != null) {
                            listener.blueToothStatusListener(true);
                        }
                        break;
                }
            }

        }

    }

    public IntentFilter getIntentFilter() {
        return intentFilter;
    }

    public interface StatusListener {

        void networkStatusListener(boolean isConnected);

        void gpsStatusListener(boolean isOpened);

        void blueToothStatusListener(boolean isOpened);

    }

}