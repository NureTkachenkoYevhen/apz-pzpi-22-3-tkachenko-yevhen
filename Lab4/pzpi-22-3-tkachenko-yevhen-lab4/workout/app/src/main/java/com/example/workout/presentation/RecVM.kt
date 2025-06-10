
package com.example.workout.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workout.data.api.dto.RecommendationDto
import com.example.workout.data.repository.RecRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecVM @Inject constructor(private val repo: RecRepo) : ViewModel() {
    var items by mutableStateOf<List<RecommendationDto>>(emptyList())
        private set
    fun load(id: Long) = viewModelScope.launch { items = repo.list(id) }
}
