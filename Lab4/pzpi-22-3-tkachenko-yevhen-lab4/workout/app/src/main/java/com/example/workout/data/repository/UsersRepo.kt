
package com.example.workout.data.repository

import com.example.workout.data.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepo @Inject constructor(private val api: ApiService) {
    suspend fun list() = api.users()
    suspend fun delete(id: Long) = api.deleteUser(id)
}
