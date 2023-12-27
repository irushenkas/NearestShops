package com.example.nearestshops.api

import com.example.nearestshops.BuildConfig
import com.example.nearestshops.dto.Location
import com.example.nearestshops.dto.Menu
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


private const val BASE_URL = "${BuildConfig.BASE_URL}/"

fun okhttp(): OkHttpClient = OkHttpClient.Builder().build()

fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface ApiService {

    @POST("auth/login")
    suspend fun loginUser(@Body body: Map<String, String>): Response<AuthState>

    @POST("auth/register")
    suspend fun registerUser(@Body body: Map<String, String>): Response<AuthState>

    @GET("locations")
    suspend fun getLocations(): Response<List<Location>>

    @GET("location/{id}/menu")
    suspend fun getMenu(@Path("id") id: Long): Response<Menu>

    data class AuthState(val id: Long = 0, val token: String? = null)
}