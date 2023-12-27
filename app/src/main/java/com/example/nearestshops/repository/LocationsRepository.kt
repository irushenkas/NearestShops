package com.example.nearestshops.repository

import com.example.nearestshops.dto.Location

interface LocationsRepository {
    suspend fun getAll(): List<Location>
}