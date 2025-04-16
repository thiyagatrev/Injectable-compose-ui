package com.thiyaga.inject.mainscreen

import com.thiyaga.inject.mainscreen.repository.ApiResponse

sealed class UIState {
    object Loading : UIState()
    data class Success(val data: ApiResponse) : UIState()
    data class Error(val message: String) : UIState()
}