
package com.example.workout.data.repository

import com.example.workout.data.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionsRepo @Inject constructor(
    private val api: ApiService,
    private val auth: AuthRepo
) {
    suspend fun list() = api.sessions(auth.userId!!)
    suspend fun create() = api.createSession(mapOf("userId" to auth.userId!!, "exerciseId" to 1))
}
