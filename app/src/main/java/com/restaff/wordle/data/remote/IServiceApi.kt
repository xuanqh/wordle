package com.restaff.wordle.data.remote

import com.restaff.wordle.data.ApiEndPoint.GET_DAILY
import com.restaff.wordle.data.ApiEndPoint.GET_RAMDOM
import com.restaff.wordle.data.ApiEndPoint.GET_WORD
import com.restaff.wordle.data.models.WordResult
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IServiceApi {
    @GET(GET_DAILY)
    fun guessDaily(
        @Query("guess") guess: String,
        @Query("size") size: Int?
    ): Single<Response<ArrayList<WordResult>>>

    @GET(GET_RAMDOM)
    fun guessRandom(
        @Query("guess") guess: String,
        @Query("size") size: Int?,
        @Query("seed") seed: Int
    ): Single<Response<ArrayList<WordResult>>>

    @GET(GET_WORD)
    fun guessWord(
        @Path("word") word: String,
        @Query("guess") guess: String
    ): Single<Response<ArrayList<WordResult>>>
}