package com.example.speechtotextconverter.speechconverter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun SpeechRecognitionRouter(
    speechRecognitionViewModel: SpeechConverterViewModel
) {
    val uiState by speechRecognitionViewModel.uiState.collectAsState()
    SpeechRecognitionScreen(
        text = uiState.text,
        onTextValueChanged = {
            speechRecognitionViewModel.onTextValueChanged(it)
        }
    )
}