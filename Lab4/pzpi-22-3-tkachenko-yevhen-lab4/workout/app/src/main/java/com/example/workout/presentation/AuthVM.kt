
package com.example.workout.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.workout.data.repository.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthVM @Inject constructor(val repo: AuthRepo) : ViewModel() {
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun login(u: String, p: String, nav: NavController) = viewModelScope.launch {
        runCatching {
            isLoading = true; repo.login(u, p)
            nav.navigate("sessions") { popUpTo(0) }
        }.onFailure { error = it.message }
        isLoading = false
    }

    fun register(u: String, p: String, e: String, nav: NavController) = viewModelScope.launch {
        runCatching {
            isLoading = true; repo.register(u, p, e)
            nav.navigate("sessions") { popUpTo(0) }
        }.onFailure { error = it.message }
        isLoading = false
    }
}
