package com.example.nearestshops.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.nearestshops.R
import com.example.nearestshops.dto.Location
import com.example.nearestshops.repository.LocationsRepository
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: LocationsRepository,
) : ViewModel() {

    val data = MutableLiveData<List<Location>>()

    fun loadLocations() = viewModelScope.launch {
        try {
            data.value = repository.getAll()
        } catch (e: Exception) {
            showErrorMessage(R.string.locations_error)
        }
    }

    private fun showErrorMessage(text: Int) {
        Toast.makeText(context, text, Toast.LENGTH_LONG)
            .show()
    }
}