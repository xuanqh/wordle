package com.restaff.wordle.presentation.wordgame

import androidx.lifecycle.MutableLiveData
import com.restaff.wordle.base.BaseViewModel
import com.restaff.wordle.data.models.ResultStatus
import com.restaff.wordle.data.models.WordResult
import com.restaff.wordle.domain.MainRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class WordleGameViewModel(private val mainRepository: MainRepository) : BaseViewModel() {
    var gameType: String?=null
    var currentRow = 0
    val maxRound = 6
    val defaultSize = 5
    val wordResults = MutableLiveData<ArrayList<WordResult>>()

    val wordleData = MutableLiveData<ArrayList<ArrayList<WordResult>>>()

    fun checkDaily(guess: String) {
        mainRepository.guessDaily(guess, defaultSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isSuccessful && it.body() != null) {
                        wordResults.value = it.body()
                    }
                },
                {
                    parseError(it)
                }
            ).addTo(compositeDisposables)
    }

    fun checkRandom(guess: String, seed: Int) {
        mainRepository.guessRandom(guess, defaultSize, seed)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe(
                {
                    if (it.isSuccessful && it.body() != null) {
                        wordResults.value = it.body()
                    }
                },
                {
                    parseError(it)
                }
            ).addTo(compositeDisposables)
    }

    fun checkWord(word: String, guess: String) {
        mainRepository.guessWord(word, guess)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe(
                {
                    if (it.isSuccessful && it.body() != null) {
                        wordResults.value = it.body()
                    }
                },
                {
                    parseError(it)
                }
            ).addTo(compositeDisposables)
    }

    fun initData() {
        Observable.fromCallable {
            val data = ArrayList<ArrayList<WordResult>>()
            for (row in 0 until maxRound) {
                val rowData = ArrayList<WordResult>()
                for (col in 0 until defaultSize) {
                    rowData.add(WordResult(col, "", ResultStatus.none.status))
                }
                data.add(rowData)
            }

            return@fromCallable data
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       wordleData.value = it
            }, {

            }).addTo(compositeDisposables)
    }

    fun checkWinGame(wordResults: ArrayList<WordResult>): Boolean {
        if(wordResults.isNotEmpty()) {
            var count = 0
            wordResults.forEach {
                if (it.result == ResultStatus.correct.status) {
                    count++
                }
            }
            return count == wordResults.size
        }else{
            return false
        }
    }
}