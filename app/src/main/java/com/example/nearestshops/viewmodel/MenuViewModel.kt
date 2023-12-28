package com.example.nearestshops.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.nearestshops.R
import com.example.nearestshops.dto.Menu
import com.example.nearestshops.repository.MenuRepository
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: MenuRepository,
) : ViewModel() {

    val data = MutableLiveData<List<Menu>>()

    fun loadMenu(locationId: Long) = viewModelScope.launch {
        try {
            data.value = repository.getAll(locationId)
        } catch (e: Exception) {
            showErrorMessage(R.string.locations_error)
        }
    }

    fun addToCart(menu: Menu) {
        try {
            repository.add(menu)
        } catch (e: Exception) {
            showErrorMessage(R.string.cart_add_error)
        }
    }

    fun removeFromCart(menu: Menu) {
        try {
            repository.remove(menu)
        } catch (e: Exception) {
            showErrorMessage(R.string.cart_remove_error)
        }
    }

    private fun showErrorMessage(text: Int) {
        Toast.makeText(context, text, Toast.LENGTH_LONG)
            .show()
    }
}