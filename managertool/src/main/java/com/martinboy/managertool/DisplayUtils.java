package com.martinboy.managertool;

import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayUtils {

    public static double getWidthPixels(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static double getHeightPixels(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static double getXdpi(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.xdpi;
    }

    public static double getYdpi(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.ydpi;
    }

    public static double getDensity(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.density;
    }

    public static double getDensityDpi(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.densityDpi;
    }

    public static double getDpConvertToPx(Context context, double dp) {

        return dp * getDensity(context);

    }

    public static double getPxConvertToDp(Context context, double px) {
        return px / getDensity(context);
    }

    public static double getPxSplitEqually(double px, int splitCount) {
        return px / splitCount;
    }

}
