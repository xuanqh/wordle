package com.restaff.wordle.data.repositoryimpl

import com.restaff.wordle.data.models.WordResult
import com.restaff.wordle.data.remote.IServiceApi
import com.restaff.wordle.domain.MainRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MainRepositoryImpl(private val serviceApi: IServiceApi) : MainRepository {


    override fun guessDaily(guess: String, size: Int?): Single<Response<ArrayList<WordResult>>> {
        return serviceApi.guessDaily(guess, size)
    }

    override fun guessRandom(
        guess: String,
        size: Int?,
        seed: Int
    ): Single<Response<ArrayList<WordResult>>> {
        return serviceApi.guessRandom(guess, size, seed)
    }

    override fun guessWord(word: String, guess: String): Single<Response<ArrayList<WordResult>>> {
        return serviceApi.guessWord(word, guess)
    }
}