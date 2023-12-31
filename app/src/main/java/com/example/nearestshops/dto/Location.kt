package com.example.nearestshops.dto

data class Location (
    val id: Long,
    val name: String,
    val point: Point
)

data class Point(
    val latitude: Double,
    val longitude: Double
)