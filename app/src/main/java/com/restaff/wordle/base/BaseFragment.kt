package com.restaff.wordle.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.restaff.wordle.base.BaseActivity
import com.restaff.wordle.base.BaseViewModel

abstract class BaseFragment<viewModel : BaseViewModel> : Fragment() {
    protected abstract val viewModel: BaseViewModel

    fun showLoadingProgress() {
        (activity as BaseActivity<*>).showLoadingProgress()
    }

    fun hideLoadingProgress() {
        (activity as BaseActivity<*>).hideLoadingProgress()
    }

    fun getDialogThemeFullScreenByOrientation(): Int {
        var theme = android.R.style.Theme_Translucent_NoTitleBar_Fullscreen
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            theme = android.R.style.Theme_Translucent_NoTitleBar
        }
        return theme;
    }

    fun getDialogThemeFullScreen(): Int {
        return android.R.style.Theme_Translucent_NoTitleBar;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}