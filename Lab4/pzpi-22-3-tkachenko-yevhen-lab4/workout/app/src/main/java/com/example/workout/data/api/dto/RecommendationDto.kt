
package com.example.workout.data.api.dto

data class RecommendationDto(
    val recommendationId: Long,
    val sessionId: Long,
    val message: String,
    val createdAt: String
)
