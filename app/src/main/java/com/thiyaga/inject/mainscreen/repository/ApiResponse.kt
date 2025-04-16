package com.thiyaga.inject.mainscreen.repository

import com.thiyaga.inject.widgets.FeatureWidget

data class ApiResponse(
    val widgets: List<FeatureWidget>
)

// Sample API Service (Replace with your actual service)
interface ApiService {
    suspend fun getWidgets(): ApiResponse
}