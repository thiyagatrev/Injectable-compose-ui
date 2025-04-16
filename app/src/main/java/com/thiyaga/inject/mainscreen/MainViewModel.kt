package com.thiyaga.inject.mainscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiyaga.inject.mainscreen.repository.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiServiceImpl
) : ViewModel() {
    var uiState by mutableStateOf<UIState>(UIState.Loading)
        private set

    init {
        fetchData()
    }

    fun fetchData() {
        uiState = UIState.Loading
        viewModelScope.launch {
            try {
                val response = apiService.getWidgets()
                uiState = UIState.Success(response)
            } catch (e: Exception) {
                uiState = UIState.Error("An error occurred: ${e.message}")
            }
        }
    }
}