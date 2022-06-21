package com.restaff.wordle.base

import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.restaff.wordle.R

abstract class BaseActivity<viewModel : BaseViewModel> : AppCompatActivity() {
    protected abstract val viewModel: BaseViewModel
    var loadingDialog: android.app.AlertDialog? = null

    fun showLoadingProgress() {
        if (loadingDialog == null) {
            val builder = android.app.AlertDialog.Builder(this, R.style.LoadingDialog)
            builder.setCancelable(true)
            builder.setView(R.layout.view_loading_dialog)
            loadingDialog = builder.create()
            loadingDialog?.show()
        }
    }

    fun hideLoadingProgress() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                currentFocus?.windowToken, 0
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideLoadingProgress()
    }
}