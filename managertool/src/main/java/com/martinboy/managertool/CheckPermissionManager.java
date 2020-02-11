package com.martinboy.managertool;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class CheckPermissionManager {

    private static CheckPermissionManager INSTANCE;
    private static final String TAG = CheckPermissionManager.class.getSimpleName();
    public static final int REQUEST_CODE_CHECK_PERMISSIONS = 120;
    //    public static String[] permissions = new String[]{
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_EXTERNAL_STORAGE
//    };

    private CheckPermissionManager() {

    }

    public static synchronized CheckPermissionManager getInstance() {

        if(INSTANCE == null)
            INSTANCE = new CheckPermissionManager();

        return INSTANCE;

    }

    public boolean checkPermissionsAllGranted(Context context, String[] permissionList) {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissionList) {
            result = ContextCompat.checkSelfPermission(context, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        Log.d(TAG, "listPermissionsNeeded.size(): " + listPermissionsNeeded.size());
        return listPermissionsNeeded.size() == 0;
    }

    public void requestPermission(Activity mAct, String[] permissionList) {
        ActivityCompat.requestPermissions(mAct, permissionList, CheckPermissionManager.REQUEST_CODE_CHECK_PERMISSIONS);
    }

    public void requestPermissionForFragment(Fragment fragment, String[] permissionList) {
        fragment.requestPermissions(permissionList, CheckPermissionManager.REQUEST_CODE_CHECK_PERMISSIONS);
    }

    public boolean checkRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults) {

        boolean permissionGrant = true;

        if (grantResults.length > 0 && permissions.length > 0 && grantResults.length == permissions.length) {

            for (int i = 0; i < permissions.length; i++) {
                Log.e(TAG, "permissions: " + permissions[i] + " grantResults: " + grantResults[i]);
            }

            for (int grantResultCode : grantResults) {
                if (grantResultCode == PackageManager.PERMISSION_DENIED) {
                    permissionGrant = false;
                    break;
                }
            }
        }

        return permissionGrant;

    }

}
