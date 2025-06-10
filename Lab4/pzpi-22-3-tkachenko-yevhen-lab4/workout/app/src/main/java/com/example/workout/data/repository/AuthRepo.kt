
package com.example.workout.data.repository

import com.example.workout.data.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepo @Inject constructor(private val api: ApiService) {
    var userId: Long? = null
        private set
    suspend fun login(u: String, p: String) {
        val dto = api.login(mapOf("username" to u, "password" to p))
        userId = dto.id
    }
    suspend fun register(u: String, p: String, e: String) {
        api.register(mapOf("username" to u, "password" to p, "email" to e))
        login(u, p)
    }
    val isAdmin get() = userId == 1L
}
