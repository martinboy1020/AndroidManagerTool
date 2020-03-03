package com.martinboy.managertool.custom_activity;

import android.view.View;

import androidx.fragment.app.Fragment;

public class BaseViewUtilsFragment extends Fragment {

    public <E extends View> E findView(int resId) {
        return ViewUtils.$(this, resId);
    }

}
