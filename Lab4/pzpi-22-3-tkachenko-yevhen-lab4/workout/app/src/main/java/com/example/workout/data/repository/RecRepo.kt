
package com.example.workout.data.repository

import com.example.workout.data.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecRepo @Inject constructor(private val api: ApiService) {
    suspend fun list(sessionId: Long) = api.recommendations(sessionId)
}
