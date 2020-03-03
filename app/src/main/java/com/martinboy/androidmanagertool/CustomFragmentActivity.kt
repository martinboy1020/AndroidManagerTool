package com.martinboy.androidmanagertool

import com.martinboy.managertool.custom_activity.BaseViewUtilsActivity

class CustomFragmentActivity : BaseViewUtilsActivity() {

    override fun getLayoutResID(): Int {
        return 0
    }

    override fun onCreateFinish() {
        transFragment(CustomFragment::class.java, null, false, false)
    }

}