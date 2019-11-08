package com.martinboy.androidmanagertool;

import android.bluetooth.BluetoothAdapter
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.martinboy.managertool.CheckConnectStatusManager
import com.martinboy.managertool.MobileConnectStateReceiver

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
        receiver = MobileConnectStateReceiver(this)
    }

    private fun checkStatus() {
        if(CheckConnectStatusManager.checkGpsEnable(this)) {

        } else {

        }

        if(CheckConnectStatusManager.checkNetWorkConnect(this)) {

        }

    }

    override fun onResume() {
        super.onResume()
        if (receiver == null)
            receiver = MobileConnectStateReceiver(this)
        val filterRefreshUpdate = IntentFilter()
//        filterRefreshUpdate.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        filterRefreshUpdate.addAction(LocationManager.PROVIDERS_CHANGED_ACTION)
        filterRefreshUpdate.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        registerReceiver(receiver, filterRefreshUpdate)
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

    }

    override fun gpsStatusListener(isOpened: Boolean) {

    }

    override fun blueToothStatusListener(isOpened: Boolean) {

    }
}
