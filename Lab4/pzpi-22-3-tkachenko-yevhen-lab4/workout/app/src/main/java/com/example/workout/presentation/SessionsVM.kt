
package com.example.workout.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workout.data.api.dto.SessionDto
import com.example.workout.data.repository.SessionsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionsVM @Inject constructor(private val repo: SessionsRepo) : ViewModel() {
    var list by mutableStateOf<List<SessionDto>>(emptyList())
        private set
    fun load() = viewModelScope.launch { list = repo.list() }
    fun create() = viewModelScope.launch { repo.create(); load() }
}
