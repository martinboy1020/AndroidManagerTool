package com.martinboy.managertool.custom_activity;

import android.app.Activity;
import android.view.View;
import android.view.Window;

import androidx.fragment.app.Fragment;

final class ViewUtils {

    private ViewUtils() {
        //throw exception
    }

    /**
     * Look for a view with the given id.
     *
     * @param object
     * @param id
     * @param <V>
     * @return
     */
    public static <V extends View> V $(Object object, int id) {
        if (object instanceof View) {
            return (V) ((View) object).findViewById(id);
        } else if (object instanceof Activity) {
            return (V) ((Activity) object).findViewById(id);
        } else if (object instanceof Window) {
            return (V) ((Window) object).findViewById(id);
        } else if (object instanceof Fragment) {
            return (V) ((Fragment) object).getView().findViewById(id);
        }
        return null;
    }
}