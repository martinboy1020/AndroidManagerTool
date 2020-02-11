package com.martinboy.androidmanagertool

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.martinboy.managertool.CheckPermissionManager
import com.martinboy.managertool.DownloadService

class DownloadActivity : AppCompatActivity() {

    private var btnDownload: Button? = null
    private var checkPermissionManager = CheckPermissionManager.getInstance()
    private var permissions = arrayOf("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE")
    private val testUrl = "http://developer.android.com/shareables/icon_templates-v4.0.zip"
    private val fileName = "icon_templates-v4.0.zip"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        btnDownload = findViewById(R.id.btn_download)
        btnDownload?.setOnClickListener {

            if (!checkPermissionManager?.checkPermissionsAllGranted(this@DownloadActivity, permissions)!!) {
                checkPermissionManager.requestPermission(this@DownloadActivity, permissions)
            } else {
                startDownload()
            }

        }

    }

    private fun startDownload() {

        val intent = Intent()
        val bundle = Bundle()
        bundle.putString(DownloadService.KEY_DOWNLOAD_URL, testUrl)
        bundle.putString(DownloadService.KEY_FILE_NAME, fileName)
        intent.putExtras(bundle)
        intent.setClass(this@DownloadActivity, DownloadService::class.java)
        startService(intent)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {

            CheckPermissionManager.REQUEST_CODE_CHECK_PERMISSIONS -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    startDownload()

                } else {
                    val noPermissions = StringBuilder()
                    for (per in permissions) {
                        noPermissions.append(per + "\n")
                    }
                    Toast.makeText(this@DownloadActivity, "Permission Denied: $noPermissions", Toast.LENGTH_SHORT).show()

                }

            }

            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }

        }

    }
}