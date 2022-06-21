package com.restaff.wordle.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.HttpException

open class BaseViewModel : ViewModel() {
    protected val compositeDisposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposables.dispose()
    }

    fun parseError(throwable: Throwable) {
        if (throwable is HttpException) {
            if (throwable.code() == 400) {
                //todo: handle error 400
            } else if (throwable.code() == 401) {
                //todo: handle error 401
            } else if (throwable.code() == 403) {
                //todo: handle error 403
            } else if (throwable.code() == 500) {
                //todo: handle error 500
            } else {
                //todo: handle unknown exception
            }
        }
    }
}
