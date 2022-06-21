package com.restaff.wordle.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.restaff.wordle.R
import com.restaff.wordle.base.BaseActivity
import com.restaff.wordle.data.models.GameType
import com.restaff.wordle.databinding.ActivityMainBinding
import com.restaff.wordle.presentation.wordgame.WordleGameActivity
import com.restaff.wordle.utils.IntentKeys
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<MainViewModel>() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setEventListener()
    }

    private fun setEventListener() {
        binding.btnGuessDaily.setOnClickListener {
            openGame(GameType.daily)
        }

        binding.btnGuessRandom.setOnClickListener {
            openGame(GameType.random)
        }

        binding.btnGuessWord.setOnClickListener {
            openGame(GameType.word)
        }

        binding.btnExit.setOnClickListener {
            finish()
        }
    }

    private fun openGame(gameType: GameType) {
        val intent = Intent(this, WordleGameActivity::class.java)
        intent.putExtra(IntentKeys.GAME_TYPE, gameType.type)
        startActivity(intent)
    }

    override val viewModel: MainViewModel
        get() = getViewModel()
}