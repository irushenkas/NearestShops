package com.example.nearestshops.repository

import com.example.nearestshops.api.ApiService
import com.example.nearestshops.error.ApiError
import com.example.nearestshops.error.NetworkError
import com.example.nearestshops.error.UnknownError
import java.io.IOException
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : AuthRepository {

    override suspend fun signIn(login: String, password: String): ApiService.AuthState {
        try {
            val body = mapOf(
                "login" to login,
                "password" to password
            )
            val response = apiService.loginUser(body)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            e.printStackTrace()
            throw NetworkError
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnknownError
        }
    }

    override suspend fun signUp(email: String, password: String): ApiService.AuthState {
        try {
            val body = mapOf(
                "login" to email,
                "password" to password
            )
            val response = apiService.registerUser(body)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}