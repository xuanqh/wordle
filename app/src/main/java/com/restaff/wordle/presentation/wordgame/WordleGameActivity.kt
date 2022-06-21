package com.restaff.wordle.presentation.wordgame

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.restaff.wordle.R
import com.restaff.wordle.base.BaseActivity
import com.restaff.wordle.data.models.GameType
import com.restaff.wordle.data.models.WordResult
import com.restaff.wordle.databinding.ActivityWordleGameBinding
import com.restaff.wordle.presentation.widgets.dialog.ConfirmDialog
import com.restaff.wordle.utils.IntentKeys
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WordleGameActivity : BaseActivity<WordleGameViewModel>() {

    lateinit var binding: ActivityWordleGameBinding
    var adapter: WordGameAdapter? = null

    override val viewModel: WordleGameViewModel
        get() = getViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wordle_game)
        initView()
        initObserver()
        viewModel.initData()

        initEventListener()
    }

    private fun initView() {
        viewModel.gameType = intent.getStringExtra(IntentKeys.GAME_TYPE)
        var title = getString(R.string.app_name)
        var description = ""
        when (viewModel.gameType) {
            GameType.daily.type -> {
                title = getString(R.string.guess_daily)
                description = getString(R.string.guess_daily_desc)
            }
            GameType.random.type -> {
                title = getString(R.string.guess_random)
                description = getString(R.string.guess_random_desc)
                binding.seedBox.visibility = View.VISIBLE
                binding.seedBox.editText?.filters =
                    arrayOf<InputFilter>(LengthFilter(viewModel.defaultSize))
            }
            GameType.word.type -> {
                description = getString(R.string.guess_word_desc)
                title = getString(R.string.guess_word)
                binding.wordBox.visibility = View.VISIBLE
                binding.wordBox.editText?.filters =
                    arrayOf<InputFilter>(LengthFilter(viewModel.defaultSize))
            }
        }

        setTitle(title)
        binding.tvDescription.text = description
    }

    private fun initEventListener() {
        binding.btnCheck.setOnClickListener {
            guessWord()
        }
    }

    private fun guessWord() {
        val guess = adapter?.getWordRow(viewModel.currentRow)
        if (!TextUtils.isEmpty(guess)) {
            if (guess?.length ?: 0 < viewModel.defaultSize) {
                Toast.makeText(this, getString(R.string.please_refill), Toast.LENGTH_SHORT)
                    .show()
            } else {
                hideSoftKeyboard()
                guess?.let {
                    showLoadingProgress()
                    when (viewModel.gameType) {
                        GameType.daily.type -> {
                            viewModel.checkDaily(it)
                        }
                        GameType.random.type -> {
                            binding.seedBox.editText?.text?.let { seed ->
                                viewModel.checkRandom(guess = it, seed = seed.toString().toInt())
                            }
                        }
                        GameType.word.type -> {
                            binding.wordBox.editText?.text?.let { word ->
                                viewModel.checkWord(guess = it, word = word.toString())
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.wordleData.observe(this) {
            it?.let { wordleData ->
                adapter = WordGameAdapter(wordleData)
                binding.wordRows.adapter = adapter
            }
        }

        viewModel.wordResults.observe(this) {
            hideLoadingProgress()
            it?.let { wordResults ->
                updateDataList(wordResults)
            }
        }
    }

    private fun updateDataList(wordResults: ArrayList<WordResult>) {

        if (viewModel.checkWinGame(wordResults = wordResults)) {
            adapter?.updateDataList(wordResults, viewModel.currentRow)
            adapter?.lockCurrentRow()
            showGameWon()
        } else {
            adapter?.updateDataList(wordResults, viewModel.currentRow)

            if (viewModel.currentRow < viewModel.maxRound - 1) {
                viewModel.currentRow++
                adapter?.updateCurrentRow(viewModel.currentRow)
            } else {
                adapter?.lockCurrentRow()
                showGameLost()
            }
        }

    }

    private fun showGameLost() {
        val confirmDialog = ConfirmDialog(this, R.style.Theme_Wordle)
        confirmDialog.setDialogMessage(
            title = getString(R.string.end_game),
            message = getString(R.string.you_lost_game),
            textYes = getString(R.string.try_again),
            textNo = getString(R.string.exit_game)
        )

        confirmDialog.getYesButton().setOnClickListener {
            confirmDialog.dismiss()
            resetGame()
        }

        confirmDialog.getNoButton().setOnClickListener {
            confirmDialog.dismiss()
            exitGame()
        }
        confirmDialog.show()
    }

    private fun exitGame() {
        finish()
    }

    private fun resetGame() {
        viewModel.currentRow =0
        viewModel.initData()
        binding.wordBox.editText?.setText("")
        binding.seedBox.editText?.setText("")
    }

    private fun showGameWon() {
        val confirmDialog = ConfirmDialog(this, R.style.Theme_Wordle)
        confirmDialog.setDialogMessage(
            title = getString(R.string.end_game),
            message = getString(R.string.congrat),
            textYes = getString(R.string.try_again),
            textNo = getString(R.string.exit_game)
        )

        confirmDialog.getYesButton().setOnClickListener {
            confirmDialog.dismiss()
            resetGame()
        }

        confirmDialog.getNoButton().setOnClickListener {
            confirmDialog.dismiss()
            exitGame()
        }
        confirmDialog.show()
    }
}