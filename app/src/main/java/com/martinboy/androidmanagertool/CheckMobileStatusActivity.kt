package com.martinboy.androidmanagertool;

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.martinboy.managertool.CheckConnectStatusManager
import com.martinboy.managertool.MobileConnectStateReceiver

private var TAG : String = CheckMobileStatusActivity::class.java.simpleName

class CheckMobileStatusActivity : AppCompatActivity(), MobileConnectStateReceiver.StatusListener {

    private var receiver: MobileConnectStateReceiver? = null
    private var textNetworkStatus: TextView? = null
    private var textGpsStatus: TextView? = null
    private var textBleStatus: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_mobile_status)
        textNetworkStatus = findViewById(R.id.text_network_status)
        textGpsStatus = findViewById(R.id.text_gps_status)
        textBleStatus = findViewById(R.id.text_bluetooth_status)
        receiver = MobileConnectStateReceiver(this, this)
        checkStatus()
    }

    @SuppressLint("SetTextI18n")
    private fun checkStatus() {
        if(CheckConnectStatusManager.checkGpsEnable(this)) {
            textGpsStatus?.text = "GPS Is Open"
        } else {
            textGpsStatus?.text = "GPS Is Not Open"
        }

        if(CheckConnectStatusManager.checkNetWorkConnect(this)) {
            if(CheckConnectStatusManager.checkConnectType(this) == CheckConnectStatusManager.NetWorkType.MobileNetWork) {
                textNetworkStatus?.text = "Network Is Open, Type is Mobile network"
            } else {
                textNetworkStatus?.text = "Network Is Open, Type is Wifi network, SSID is " + CheckConnectStatusManager.getWifiSsid(this);
            }
        } else {
            textNetworkStatus?.text = "Network Is not Open"
        }

        if(CheckConnectStatusManager.checkBluetoothEnable()) {
            textBleStatus?.text = "Ble Is Open"
        } else {
            textBleStatus?.text = "Ble Is not Open"
        }

    }

    private fun checkNetStatus() {

        if(CheckConnectStatusManager.checkNetWorkConnect(this)) {
            textNetworkStatus?.text = "Network Is Open"
        } else {
            textNetworkStatus?.text = "Network Is Not Open"
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        // Checks the orientation of the screen
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
        } else if (newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT){
//            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        if (receiver == null)
            receiver = MobileConnectStateReceiver(this, this)
        registerReceiver(receiver, receiver?.intentFilter)
    }

    override fun onPause() {
        super.onPause()
        if (receiver != null)
            unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (receiver != null)
            unregisterReceiver(receiver)
    }

    override fun networkStatusListener(isConnected: Boolean) {
        Log.d(TAG, "networkStatus isConnected: $isConnected")
        checkStatus()
    }

    override fun gpsStatusListener(isOpened: Boolean) {
        Log.d(TAG, "gpsStatus isOpened: $isOpened")
        checkStatus()
    }

    override fun blueToothStatusListener(isOpened: Boolean) {
        Log.d(TAG, "blueToothStatus isOpened: $isOpened")
        checkStatus()
    }
}
