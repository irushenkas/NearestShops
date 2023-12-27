package ru.netology.nmedia.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.nearestshops.R
import com.example.nearestshops.repository.AuthRepository
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: AuthRepository,
) : ViewModel() {

    fun signIn(login: String, password: String) = viewModelScope.launch {
        try {
            val authState = repository.signIn(login, password)

//            if(authState.token != null) {
//                auth.setAuth(authState.id, authState.token)
//            } else {
//                showErrorMessage(R.string.login_error)
//            }
        } catch (e: Exception) {
            showErrorMessage(R.string.login_error)
            e.printStackTrace()
        }
    }

    fun signUp(email: String, password: String, confirmPassword: String) = viewModelScope.launch {
        try {
            if(password != confirmPassword) {
                throw java.lang.IllegalArgumentException("Password mismatch")
            }

            val authState = repository.signUp(email, password)

//            if(authState.token != null) {
//                auth.setAuth(authState.id, authState.token)
//            } else {
//                showErrorMessage(R.string.register_error)
//            }
        } catch (e: Exception) {
            showErrorMessage(R.string.register_error)
            e.printStackTrace()
        }
    }

    private fun showErrorMessage(text: Int) {
        Toast.makeText(context, text, Toast.LENGTH_LONG)
            .show()
    }

}