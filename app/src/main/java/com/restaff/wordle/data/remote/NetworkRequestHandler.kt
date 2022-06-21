package com.restaff.wordle.data.remote

import android.app.Application
import com.restaff.wordle.utils.PrefKeys
import com.restaff.wordle.utils.SharedPrefUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkRequestHandler(private val application: Application) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return buildResponse(application, chain)
    }

    private fun buildResponse(application: Application, chain: Interceptor.Chain): Response {
        val token = SharedPrefUtils.getStringData(application, PrefKeys.KEY_TOKEN).orEmpty()
        val original: Request = chain.request()
        val request: Request = if (token.isNotEmpty()) {
            original.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .method(original.method, original.body)
                .build()
        } else {
            original.newBuilder()
                .method(original.method, original.body)
                .build()
        }
        return chain.proceed(request)
    }
}