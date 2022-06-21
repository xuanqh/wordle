package com.restaff.wordle.di

import com.restaff.wordle.presentation.main.MainViewModel
import com.restaff.wordle.presentation.wordgame.WordleGameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }

    viewModel {
        WordleGameViewModel(get())
    }
}