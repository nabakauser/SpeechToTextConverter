package com.example.speechtotextconverter.speechconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpeechConverterActivity : ComponentActivity() {
    private val speechRecognitionViewModel: SpeechConverterViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpeechRecognitionRouter(speechRecognitionViewModel = speechRecognitionViewModel )
        }
    }
}


