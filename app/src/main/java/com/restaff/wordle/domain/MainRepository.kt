package com.restaff.wordle.domain

import com.restaff.wordle.data.ApiEndPoint
import com.restaff.wordle.data.models.WordResult
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface MainRepository {
    fun guessDaily(
        guess: String,
        size: Int?
    ): Single<Response<ArrayList<WordResult>>>


    fun guessRandom(
        guess: String,
        size: Int?,
        seed: Int
    ): Single<Response<ArrayList<WordResult>>>

    @GET(ApiEndPoint.GET_WORD)
    fun guessWord(
        word: String,
        guess: String
    ): Single<Response<ArrayList<WordResult>>>
}