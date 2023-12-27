package com.example.nearestshops.repository

import com.example.nearestshops.dto.Menu

interface MenuRepository {
    suspend fun getAll(locationId: Long): List<Menu>
}