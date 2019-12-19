package com.martinboy.managertool;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class CheckConnectStatusManager {

    public static boolean checkGpsEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager != null) {
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } else {
            return false;
        }
    }

    public static boolean checkBluetoothEnable() {

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            // device can not support ble.
            return false;
        } else {
            if (bluetoothAdapter.isEnabled()) {
                return true;
            } else {
                return false;
            }
        }

    }

    public boolean checkInternet(Context context) {
        if (checkConnectType(context) == NetWorkType.MobileNetWork) {
            return checkMobileConnected(context);
        } else {
            return checkWifiEnable(context);
        }
    }

    public static NetWorkType checkConnectType(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (manager != null) {
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null) {

                if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return NetWorkType.MobileNetWork;
                } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    return NetWorkType.WIFiNetWork;
                } else {
                    return null;
                }

            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    private boolean checkMobileConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (manager != null) {
            networkInfo = manager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            if (networkInfo.isConnected()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean checkWifiEnable(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiManager != null) {
            if (wifiManager.isWifiEnabled()) {
                if(checkInternetConnect(context)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    private boolean checkInternetConnect(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);

        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();

            if (info == null || !info.isConnected()) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public static boolean checkNetWorkConnect(Context context) {

        if (checkConnectType(context) == NetWorkType.MobileNetWork) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (manager != null) {
                networkInfo = manager.getActiveNetworkInfo();
            }
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                if (networkInfo.isConnected()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            if (wifiManager != null) {
                if (wifiManager.isWifiEnabled()) {
                    ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);

                    if (manager != null) {
                        NetworkInfo info = manager.getActiveNetworkInfo();

                        if (info == null || !info.isConnected()) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

    }

    public static String getWifiSsid(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        return info.getSSID();
    }

    public enum NetWorkType {

        MobileNetWork, WIFiNetWork

    }

}