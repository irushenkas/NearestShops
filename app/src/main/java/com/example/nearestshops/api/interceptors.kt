package com.example.nearestshops.api

import com.example.nearestshops.auth.AppAuth
import okhttp3.Interceptor
import okhttp3.Response


fun authInterceptor(auth: AppAuth) = fun(chain: Interceptor.Chain): Response {
    auth.authStateFlow.value.token?.let { token ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)
    }

    return chain.proceed(chain.request())
}