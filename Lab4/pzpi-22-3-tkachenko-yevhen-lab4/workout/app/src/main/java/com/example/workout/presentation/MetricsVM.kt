
package com.example.workout.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workout.data.api.dto.MetricDto
import com.example.workout.data.repository.MetricsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MetricsVM @Inject constructor(private val repo: MetricsRepo) : ViewModel() {
    var data by mutableStateOf<List<MetricDto>>(emptyList())
        private set
    fun load(id: Long) = viewModelScope.launch { data = repo.list(id) }
}
