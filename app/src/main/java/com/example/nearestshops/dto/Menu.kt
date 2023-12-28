package com.example.nearestshops.dto

data class Menu (
    val id: Long,
    val name: String,
    val imageURL: String,
    val price: Int,
    var count: Int = 0
)