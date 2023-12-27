package com.example.nearestshops.repository

import com.example.nearestshops.api.ApiService

interface AuthRepository {
    suspend fun signIn(login: String, password: String): ApiService.AuthState
    suspend fun signUp(email: String, password: String): ApiService.AuthState
}