package com.restaff.wordle.presentation.wordgame

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import com.restaff.wordle.R
import com.restaff.wordle.base.BaseCustomView
import com.restaff.wordle.data.models.ResultStatus
import com.restaff.wordle.data.models.WordResult
import com.restaff.wordle.databinding.CharItemBinding

class CharView(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : BaseCustomView(context, attrs, defStyleAttr) {

    private var binding: CharItemBinding
    private var wordResult: WordResult? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = CharItemBinding.inflate(inflater)
        addView(binding.root)
    }

    fun getEditText(): EditText {
        return binding.txtLetter
    }

    fun bindData(wordResult: WordResult) {
        this.wordResult = wordResult
        binding.txtLetter.setText(wordResult.guess)
        binding.txtLetter.tag = wordResult.slot
        var bgRes = R.drawable.bg_edittext
        var textColor = ContextCompat.getColor(binding.root.context, R.color.white)

        when (wordResult.result) {
            ResultStatus.correct.status -> {
                bgRes = R.drawable.bg_edittext_correct
            }
            ResultStatus.present.status -> {
                bgRes = R.drawable.bg_edittext_present
            }
            ResultStatus.absent.status -> {
                bgRes = R.drawable.bg_edittext_absent
            }
            ResultStatus.none.status -> {
                textColor =
                    ContextCompat.getColor(binding.root.context, R.color.word_text_color)
            }
        }

        binding.txtLetter.setBackgroundResource(bgRes)
        binding.txtLetter.setTextColor(textColor)
    }
}