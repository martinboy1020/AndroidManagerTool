package com.martinboy.managertool;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SharePreferenceManager {

    public static void addStringToSharePreference(Context context, String sharePreferenceName, String dataTag, String data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(dataTag, data).apply();
    }

    public static String getStringFromSharePreference(Context context, String sharePreferenceName, String dataTag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.getString(dataTag, "");
    }

    public static void addIntToSharePreference(Context context, String sharePreferenceName, String dataTag, int data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(dataTag, data).apply();
    }

    public static int getIntegerFromSharePreference(Context context, String sharePreferenceName, String dataTag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(dataTag, -1);
    }

    public static void addBooleanToSharePreference(Context context, String sharePreferenceName, String dataTag, boolean data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(dataTag, data).apply();
    }

    public static boolean getBooleanFromSharePreference(Context context, String sharePreferenceName, String dataTag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(dataTag, false);
    }

    public static void addFloatToSharePreference(Context context, String sharePreferenceName, String dataTag, float data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(dataTag, data).apply();
    }

    public static float getFloatFromSharePreference(Context context, String sharePreferenceName, String dataTag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(dataTag, -1);
    }

    public static void addLongToSharePreference(Context context, String sharePreferenceName, String dataTag, long data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(dataTag, data).apply();
    }

    public static float getLongFromSharePreference(Context context, String sharePreferenceName, String dataTag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(dataTag, -1);
    }

    public static void addStringSetToSharePreference(Context context, String sharePreferenceName, String dataTag, Set<String> data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putStringSet(dataTag, data).apply();
    }

    public static Set<String> getSetStringFromSharePreference(Context context, String sharePreferenceName, String dataTag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.getStringSet(dataTag, null);
    }

//    public static void addObjectToSharePreference(Context context, String sharePreferenceName, String dataTag, Object data) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
//        Gson gson = GsonUtil.getCustomGson();
//        String json = gson.toJson(data);
////        Log.d("tag1 addUploadImageInHistory", "json: " + json);
//        sharedPreferences.edit().putString(dataTag, json).apply();
//    }

//    public static void addUploadImageInHistory(Context context, String sharePreferenceName, String dataTag, Object object) {
//        List<Object> imgList = getUploadImageHistoryList(context, sharePreferenceName, dataTag);
//        if(imgList != null) {
//            imgList.add(object);
//        } else {
//            imgList = new ArrayList<>();
//            imgList.add(object);
//        }
//
//        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
//        Gson gson = GsonUtil.getCustomGson();
//        String json = gson.toJson(imgList);
////        Log.d("tag1 addUploadImageInHistory", "json: " + json);
//        sharedPreferences.edit().putString(dataTag, json).apply();
//    }
//
//    public static List<Object> getUploadImageHistoryList(Context context, String sharePreferenceName, String dataTag) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(sharePreferenceName, Context.MODE_PRIVATE);
//        Gson gson = GsonUtil.getCustomGson();
//        String stringJson = sharedPreferences.getString(dataTag, "");
////        Log.d("tag1 getUploadImageHistoryList", "stringJson: " + stringJson);
//        if (!stringJson.equals("")) {
//            return (List<Object>) GsonUtil.JsonToArrayList(stringJson, new TypeToken<List<Object>>(){}.getType());
//        } else {
//            return null;
//        }
//    }

}

