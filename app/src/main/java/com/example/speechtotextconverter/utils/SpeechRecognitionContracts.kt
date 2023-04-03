package com.example.speechtotextconverter.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.view.Gravity
import androidx.activity.result.contract.ActivityResultContract
import java.util.*
import kotlin.collections.ArrayList

class SpeechRecognitionContracts: ActivityResultContract<Unit, ArrayList<String>?>() {
    override fun createIntent(context: Context, input: Unit): Intent {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.ACTION_RECOGNIZE_SPEECH,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something")
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, Gravity.BOTTOM)

        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): ArrayList<String>? {
        return if(resultCode != Activity.RESULT_OK) {
            null
        } else {
            intent?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
        }
    }
}