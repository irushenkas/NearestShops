package com.example.nearestshops.repository

import com.example.nearestshops.api.ApiService
import com.example.nearestshops.dto.Menu
import com.example.nearestshops.error.ApiError
import com.example.nearestshops.error.NetworkError
import com.example.nearestshops.error.UnknownError
import java.io.IOException
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MenuRepository {

    override suspend fun getAll(locationId: Long): List<Menu> {
        try {
            val response = apiService.getMenu(locationId)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }

            val body: List<Menu> = response.body() ?: throw ApiError(response.code(), response.message())
            return body
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}