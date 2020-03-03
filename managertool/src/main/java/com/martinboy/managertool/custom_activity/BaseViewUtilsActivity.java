package com.martinboy.managertool.custom_activity;

import android.os.Bundle;
import android.view.View;

import com.martinboy.managertool.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseViewUtilsActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    int layoutResID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadContentView();
        onCreateFinish();
    }

    public abstract void onCreateFinish();

    public abstract int getLayoutResID();

    public <E extends View> E getView(int resId) {
        return ViewUtils.$(this, resId);
    }

    protected void loadContentView() {
        layoutResID = getLayoutResID() > 0 ? getLayoutResID() : R.layout.activity_base_view_utils;
        setContentView(layoutResID);
    }

    protected void loadContentView(int resId) {
        setContentView(resId > 0 ? resId : 0);
    }

    //直接使用初始的Layout來替換Fragment
    public void transFragment(Class<? extends Fragment> fragmentClass, Bundle bundle, boolean backStack, boolean isReplaceFragment) {

        if (fragmentManager == null) {
            fragmentManager = this.getSupportFragmentManager();
        }

        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();

        Fragment fragment = null;

        if (isReplaceFragment) {
            setFragmentByReplace(beginTransaction, fragmentClass, bundle, backStack, R.id.layout_fragment);
        } else {

            if (fragmentManager.findFragmentByTag(fragmentClass.getSimpleName()) != null) {
                fragment = fragmentManager.findFragmentByTag(fragmentClass.getSimpleName());
            } else {
                try {
                    fragment = fragmentClass.newInstance();
                    beginTransaction.add(R.id.layout_fragment, fragment);
                    if (backStack) {
                        beginTransaction.addToBackStack(fragment.getClass().getSimpleName());
                    } else {
                        beginTransaction.disallowAddToBackStack();
                    }
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }

            for (Fragment f : fragmentManager.getFragments()) {
                beginTransaction.hide(f);
            }

            if (fragment != null) {

                if (bundle != null) {
                    fragment.setArguments(bundle);
                }

                beginTransaction.show(fragment);
            }

            beginTransaction.commit();

        }

    }

    //自定義的layout替換Fragment
    public void transFragment(Class<? extends Fragment> fragmentClass, Bundle bundle, boolean backStack, boolean isReplaceFragment, int replaceResId) {

        if (fragmentManager == null) {
            fragmentManager = this.getSupportFragmentManager();
        }

        int resId = replaceResId != 0 ? replaceResId : R.id.layout_fragment;

        if (resId == R.id.layout_fragment && layoutResID != R.layout.activity_base_view_utils)
            return;

        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();

        Fragment fragment = null;

        if (isReplaceFragment) {
            setFragmentByReplace(beginTransaction, fragmentClass, bundle, backStack, resId);
        } else {

            if (fragmentManager.findFragmentByTag(fragmentClass.getSimpleName()) != null) {
                fragment = fragmentManager.findFragmentByTag(fragmentClass.getSimpleName());
            } else {
                try {
                    fragment = fragmentClass.newInstance();
                    if (replaceResId != 0)
                        beginTransaction.add(R.id.layout_fragment, fragment);
                    if (backStack) {
                        beginTransaction.addToBackStack(fragment.getClass().getSimpleName());
                    } else {
                        beginTransaction.disallowAddToBackStack();
                    }
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }

            for (Fragment f : fragmentManager.getFragments()) {
                beginTransaction.hide(f);
            }

            if (fragment != null) {

                if (bundle != null) {
                    fragment.setArguments(bundle);
                }

                beginTransaction.show(fragment);
                beginTransaction.commit();
            }

        }

    }

    private void setFragmentByReplace(FragmentTransaction beginTransaction,
                                      Class<? extends Fragment> fragmentClass,
                                      Bundle bundle,
                                      boolean backStack,
                                      int replaceResId) {

        Fragment fragment;

        try {
            fragment = fragmentClass.newInstance();
            beginTransaction.replace(replaceResId, fragment);
            if (backStack) {
                beginTransaction.addToBackStack(fragment.getClass().getSimpleName());
            } else {
                beginTransaction.disallowAddToBackStack();
            }

            if (bundle != null) {
                fragment.setArguments(bundle);
            }

            beginTransaction.commit();

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

}
