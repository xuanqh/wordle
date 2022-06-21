package com.restaff.wordle.base

import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.os.Parcel
import android.os.Parcelable

open class BaseDialog(context: Context, themId: Int) : Dialog(context, themId) {

    fun getDialogThemeFullScreenByOrientation(): Int {
        var theme = android.R.style.Theme_Translucent_NoTitleBar_Fullscreen
        val orientation = context.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            theme = android.R.style.Theme_Translucent_NoTitleBar
        }
        return theme;
    }

    fun getDialogThemeFullScreen(): Int {
        return android.R.style.Theme_Translucent_NoTitleBar;
    }
}