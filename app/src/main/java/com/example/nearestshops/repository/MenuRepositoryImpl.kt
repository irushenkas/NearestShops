package com.example.nearestshops.repository

import android.content.Context
import com.example.nearestshops.api.ApiService
import com.example.nearestshops.dto.Menu
import com.example.nearestshops.error.ApiError
import com.example.nearestshops.error.NetworkError
import com.example.nearestshops.error.UnknownError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: ApiService
) : MenuRepository {

    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Menu::class.java).type
    private val filename = "nearest_shop_cart.json"
    private var menuList = mutableListOf<Menu>()

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

    override fun add(menu: Menu) {
        var contains = false
        for(item in menuList) {
            if(item.id == menu.id) {
                contains = true
            }
        }
        if(!contains) {
            menuList.add(menu)
        }
        sync()
    }

    override fun remove(menu: Menu) {
        var remove = false
        for(item in menuList) {
            if(item.id == menu.id) {
                if(item.count == 0) {
                    remove = true
                }
            }
        }
        if(remove) {
            menuList.remove(menu)
        }
        sync()
    }

    override fun getCart(): List<Menu> {
        val file = context.filesDir.resolve(filename)
        if (file.exists()) {
            context.openFileInput(filename).bufferedReader().use {
                menuList = gson.fromJson(it, type)
            }
        }
        return menuList
    }

    private fun sync() {
        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(menuList))
        }
    }
}