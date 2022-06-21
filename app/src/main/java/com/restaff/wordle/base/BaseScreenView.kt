package com.restaff.wordle.base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.lifecycle.LifecycleOwner
import com.restaff.wordle.R

abstract class BaseScreenView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    lateinit var viewOwner: LifecycleOwner

    init {

    }

    abstract fun bindViewModel(viewModel: BaseViewModel, viewOwner: LifecycleOwner)

    var loadingDialog: android.app.AlertDialog? = null
    fun showLoadingProgress(context: Context) {
        if (loadingDialog == null) {
            val builder = android.app.AlertDialog.Builder(context, R.style.LoadingDialog)
            builder.setCancelable(false)
            builder.setView(R.layout.view_loading_dialog)
            loadingDialog = builder.create()
            loadingDialog?.show()
        }
    }

    fun hideLoadingProgress() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }
}