package com.martinboy.androidmanagertool

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.martinboy.androidmanagertool.customthread.CustomRunnable
import com.martinboy.androidmanagertool.customthread.CustomThread
import com.martinboy.managertool.custom_activity.BaseViewUtilsFragment
import java.lang.ref.WeakReference

class CustomFragment2 : BaseViewUtilsFragment() {

    private var btnGo: Button? = null
    private var btnStartThread: Button? = null
    private var text1: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_custom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGo = findView(R.id.btn_go)
        btnStartThread = findView(R.id.btn_start_thread)
        text1 = findView(R.id.text1)
        btnGo?.text = "這是第二頁"
        btnStartThread?.text = "啟動子線程"

//        btnGo!!.setOnClickListener {
//            if (this@CustomFragment2.activity != null) {
//                val mAct = this@CustomFragment2.activity as CustomFragmentActivity?
//                //                    mAct.switchFragment(SecondFragment.class, null, true);
//                mAct?.transFragment(CustomFragment2::class.java, null, false, false)
//            }
//        }

        btnStartThread!!.setOnClickListener { startThread() }
    }

    private fun startThread() {
        val customThread = CustomThread()
        customThread.start()
        val customRunnable = Thread(CustomRunnable(object : CustomRunnable.CallBack {
            override fun returnValue(word: String?) {
                if (this@CustomFragment2.activity == null) return
                this@CustomFragment2.activity?.runOnUiThread { text1!!.text = word }
            }
        }))
        customRunnable.start()
        val thread = Thread(Runnable {
            Log.d("tag1", "thread run")
            Looper.prepare()
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Log.d("tag1", "This Looper is Main Looper")
            } else {
                Log.d("tag1", "This Looper is not Main Looper")
            }
            val handler: Handler = object : Handler(Looper.myLooper()) {
                override fun handleMessage(msg: Message) {
                    Log.d("tag1", "handler handleMessage")
                    super.handleMessage(msg)
                    if (msg.what == 0) {
                        Log.d("tag1", "handler sendMessage 0")
                    }
                }
            }
            handler.sendEmptyMessage(0.also { Message().what = it })
            Looper.loop()
        })
        thread.start()
    }

    internal class WkHandler(activity: Activity) : Handler() {
        var mWk: WeakReference<Activity> = WeakReference(activity)
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val activity = mWk.get()
            if (activity != null) {
            }
        }

    }

}