package com.martinboy.androidmanagertool

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//
//    class ApiCallBack : VolleyTool.VolleyToolCallBack {
//        override fun callBackStringResponse(response: String?) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//        override fun callBackStringResponseError(error: String?) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//        // 覆寫Printable01介面的print抽象函式
//
//    }
//
//    private fun testVolleyTool() {
//        val volleyTool = VolleyTool()
//        val url = "http://secondhandbs.000webhostapp.com/Fubon/PunchIn.php?account=michael&fbclid=IwAR1WxqKQPpzzhOqeA_Kvl7atRk_eMtVW3Jik7qdaBbVXMhbS-bjG_VAajgo"
//        val callBack = ApiCallBack()
//        volleyTool.runNetApi(this, url, VolleyTool.VolleyToolMethod.METHOD_POST ,callBack)
//    }

    @BindView(R.id.btn_check_mobile_status_test) @JvmField var btnCheckMobileStatus: Button? = null
    @BindView(R.id.btn_download_test) @JvmField var btnDownload: Button? = null
    @BindView(R.id.btn_sp_test) @JvmField var btnSpTest: Button? = null
    @BindView(R.id.btn_change_fragment) @JvmField var btnChangeFragment: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        btnCheckMobileStatus?.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, CheckMobileStatusActivity::class.java)
            startActivity(intent)
        }

        btnDownload?.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, DownloadActivity::class.java)
            DownloadActivity.test = "this is test"
            startActivity(intent)
        }

        btnSpTest?.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, SharePreferenceTestActivity::class.java)
            startActivity(intent)
        }

        btnChangeFragment?.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, CustomFragmentActivity::class.java)
            startActivity(intent)
        }

    }



}
