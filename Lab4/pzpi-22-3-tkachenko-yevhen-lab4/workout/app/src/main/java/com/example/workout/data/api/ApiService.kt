
package com.example.workout.data.api

import com.example.workout.data.api.dto.*
import retrofit2.http.*

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body body: Map<String, String>): AuthDto

    @POST("auth/register")
    suspend fun register(@Body body: Map<String, String>): AuthDto

    @GET("sessions/user/{userId}")
    suspend fun sessions(@Path("userId") id: Long): List<SessionDto>

    @POST("sessions")
    suspend fun createSession(@Body body: Map<String, Long>): SessionDto

    @GET("metrics/{sessionId}")
    suspend fun metrics(@Path("sessionId") id: Long): List<MetricDto>

    @POST("recommendations/{sessionId}")
    suspend fun recommendations(@Path("sessionId") id: Long): List<RecommendationDto>

    @GET("users")
    suspend fun users(): List<UserDto>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Long)
}
