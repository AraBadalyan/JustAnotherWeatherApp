package com.arabadalyan.openweathermap.view.fragments.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.arabadalyan.openweathermap.view.activities.base.BaseActivity

abstract class BaseFragment : Fragment() {

    lateinit var mActivity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as BaseActivity
    }
}