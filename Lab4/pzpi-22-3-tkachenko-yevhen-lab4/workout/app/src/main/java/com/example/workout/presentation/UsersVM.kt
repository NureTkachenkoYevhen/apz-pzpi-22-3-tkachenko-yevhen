
package com.example.workout.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workout.data.api.dto.UserDto
import com.example.workout.data.repository.UsersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersVM @Inject constructor(private val repo: UsersRepo) : ViewModel() {
    var users by mutableStateOf<List<UserDto>>(emptyList())
        private set
    fun load() = viewModelScope.launch { users = repo.list() }
    fun delete(id: Long) = viewModelScope.launch { repo.delete(id); load() }
}
