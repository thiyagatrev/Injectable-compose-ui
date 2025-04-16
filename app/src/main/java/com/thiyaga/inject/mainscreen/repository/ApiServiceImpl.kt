package com.thiyaga.inject.mainscreen.repository

import com.thiyaga.inject.widgets.FeatureWidget
import javax.inject.Inject

class ApiServiceImpl @Inject constructor() : ApiService {
    override suspend fun getWidgets(): ApiResponse {
        // Simulate an API call with some delay
        kotlinx.coroutines.delay(1000) // Simulate a network delay
        // Return sample data
        return ApiResponse(
            listOf(
                FeatureWidget(id = "1", identifier = "text", sortSequence = 1, data = """{"text": "Hello from Text Widget"}"""),
                FeatureWidget(id = "2", identifier = "image", sortSequence = 2, data = """{"url": "https://example.com/image.jpg"}"""),
                FeatureWidget(id = "3", identifier = "button", sortSequence = 3, data = """{"label": "Click Me", "action": "open_url"}"""),
                FeatureWidget(id = "4", identifier = "text", sortSequence = 4, data = """{"text": "Hello from Text Widget"}"""),
                FeatureWidget(id = "5", identifier = "image", sortSequence = 5, data = """{"url": "https://example.com/image.jpg"}"""),
                FeatureWidget(id = "6", identifier = "button", sortSequence = 6, data = """{"label": "Click Me", "action": "open_url"}"""),
                FeatureWidget(id = "7", identifier = "text", sortSequence = 7, data = """{"text": "Hello from Text Widget"}"""),
                FeatureWidget(id = "8", identifier = "image", sortSequence = 8, data = """{"url": "https://example.com/image.jpg"}"""),
                FeatureWidget(id = "11", identifier = "unknown", sortSequence = 19, data = """{"label": "Click Me", "action": "open_url"}"""),
                FeatureWidget(id = "9", identifier = "button", sortSequence = 9, data = """{"label": "Click Me", "action": "open_url"}"""),
                FeatureWidget(id = "10", identifier = "text", sortSequence = 10, data = """{"text": "Hello from Text Widget"}""")
            )
        )
    }
}
