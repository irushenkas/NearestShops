package com.example.nearestshops.repository

import com.example.nearestshops.dto.Menu

interface MenuRepository {
    suspend fun getAll(locationId: Long): List<Menu>
    fun add(menu: Menu)
    fun remove(menu: Menu)
    fun getCart(): List<Menu>
}