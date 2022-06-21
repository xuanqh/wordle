package com.restaff.wordle.presentation.widgets.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import com.restaff.wordle.R
import com.restaff.wordle.databinding.DialogConfirmBinding
import com.restaff.wordle.presentation.widgets.animation.MyBounceInterpolator


class ConfirmDialog(context: Context, themId: Int) : Dialog(context, themId) {
    private var binding: DialogConfirmBinding = DataBindingUtil.inflate(
        LayoutInflater.from(getContext()),
        R.layout.dialog_confirm,
        null,
        false
    )

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        setCancelable(false)
        setCanceledOnTouchOutside(false)

        val myAnim: Animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
        val interpolator = MyBounceInterpolator(0.1, 15.0)
        myAnim.interpolator = interpolator
        binding.btnNo.paintFlags =
            binding.btnNo.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        myAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.btnNo.paintFlags =
                    binding.btnNo.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })
        binding.cardView.startAnimation(myAnim)

        onCLick()
    }

    private fun onCLick() {
        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }

    fun getYesButton(): AppCompatButton {
        return binding.btnYes
    }

    fun getNoButton(): TextView {
        return binding.btnNo
    }

    fun setDialogMessage(title: String, message: String, textYes: String, textNo: String) {
        binding.txtTitle.text = title
        binding.txtMessage.text = message
        binding.btnYes.text = textYes
        binding.btnNo.text = textNo
    }
}