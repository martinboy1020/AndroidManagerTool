package com.martinboy.managertool;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DownloadService extends Service {

    private static String tmpDbDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    public static final String KEY_DOWNLOAD_URL = "download_url";
    public static final String KEY_FILE_NAME = "file_name";
    private DownloadManager downloadManager;
    private DownloadBinder binder;
    private DownLoadBroadcast downLoadBroadcast;
    private ArrayList<Long> downloadIdList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
//        binder = new DownloadBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 執行任務
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null && bundle.containsKey(KEY_DOWNLOAD_URL)) {
                String url = bundle.getString(KEY_DOWNLOAD_URL);
                String fileName = bundle.getString(KEY_FILE_NAME);
                if (checkString(url) && checkString(fileName)) {
                    setDownload(url, fileName);
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private boolean checkString(String word) {

        return word != null && !word.equals("");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        downloadUrl =  intent.getStringExtra(KEY_DOWNLOAD_URL);
//        setDownload(testUrl);
        return binder;
    }

    public class DownloadBinder extends Binder {

        public void startDownload(String url, String fileName) {
            setDownload(url, fileName);
        }

        /**
         * 返回当前服务的实例
         *
         * @return
         */
        public DownloadService getService() {
            return DownloadService.this;
        }

    }

    private void registerBroadcast() {

        if (downLoadBroadcast != null) {
            /* 注册service 广播 1.任务完成时 2.进行中的任务被点击*/
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
            intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
            registerReceiver(downLoadBroadcast = new DownLoadBroadcast(), intentFilter);
        }

    }

    private void unregisterBroadcast() {
        if (downLoadBroadcast != null) {
            unregisterReceiver(downLoadBroadcast);
            downLoadBroadcast = null;
        }
    }

    private void setDownload(String url, String fileName) {
        registerBroadcast();
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
//      指定只能使用Wifi下載
//      request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
//      是否允許使用手機網路下載
//      request.setAllowedOverRoaming(true);
        File file = new File(tmpDbDir, fileName);
        request.setDestinationUri(Uri.fromFile(file));
//        request.setDestinationInExternalFilesDir(this, tmpDbDir, "fileName");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(true);
        request.allowScanningByMediaScanner();
        downloadIdList.add(downloadManager.enqueue(request));
    }

    private class DownLoadBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent == null || intent.getAction() == null)
                return;

            long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            if (downId == -1)
                return;

            switch (intent.getAction()) {
                case DownloadManager.ACTION_DOWNLOAD_COMPLETE:

                    if (downloadIdList != null && downloadIdList.size() > 0) {

                        for (long id : downloadIdList) {
                            if (id == downId) {
                                Uri downIdUri = downloadManager.getUriForDownloadedFile(id);
                                downloadIdList.remove(id);

                                if (downIdUri != null)
                                    Toast.makeText(DownloadService.this, "File Download Complete", Toast.LENGTH_SHORT).show();

                                break;
                            }
                        }

                    }

                    break;

                case DownloadManager.ACTION_NOTIFICATION_CLICKED:
                    Toast.makeText(DownloadService.this, "During Download", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (downloadIdList != null) {

            if(downloadManager != null) {

                for(long id : downloadIdList) {
                    downloadManager.remove(id);
                }

            }

            downloadIdList.clear();
            downloadIdList = null;
        }

        unregisterBroadcast();

    }

}
