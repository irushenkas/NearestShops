package com.example.nearestshops.repository

import com.example.nearestshops.api.ApiService
import com.example.nearestshops.dto.Location
import com.example.nearestshops.error.ApiError
import com.example.nearestshops.error.NetworkError
import com.example.nearestshops.error.UnknownError
import java.io.IOException
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LocationsRepository {

    override suspend fun getAll(): List<Location> {
        try {
            val response = apiService.getLocations()
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }

            val body: List<Location> = response.body() ?: throw ApiError(response.code(), response.message())
            return body
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}