package com.example.speechtotextconverter.speechconverter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class SpeechConverterViewModel: ViewModel() {
    private val viewModelState = MutableStateFlow(SpeechConverterViewModelState())

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())

    fun onTextValueChanged(text:String){
        viewModelState.update {
            it.copy(
                text = text
            )
        }
    }
}

data class SpeechConverterViewModelState(
    var text: String? = "",
){
    fun toUiState(): ConverterUiState {
        return ConverterUiState(
            text = text,
        )
    }
}

data class ConverterUiState(
    var text: String?,
)