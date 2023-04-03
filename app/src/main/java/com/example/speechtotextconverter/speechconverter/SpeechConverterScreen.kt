package com.example.speechtotextconverter.speechconverter

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speechtotextconverter.R
import com.example.speechtotextconverter.utils.SpeechRecognitionContracts
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.util.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SpeechRecognitionScreen(
    text: String?,
    onTextValueChanged: (String) -> Unit,
) {
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.RECORD_AUDIO
    )
    SideEffect {
        permissionState.launchPermissionRequest()
    }
    val speechRecogniserLauncher = rememberLauncherForActivityResult(
        contract = SpeechRecognitionContracts(),
        onResult = {
            it?.joinToString(separator = "")?.let { it1 -> onTextValueChanged(it1) }
        }
    )

//joinToString() function takes a separator
// string and returns a concatenated string of all the elements
// in the array, separated by the separator.

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if(text != null) {
            Text(
                text = text.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.SansSerif
                ),
            )
        }
        Spacer(modifier = Modifier.padding(30.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_microphone),
            contentDescription ="mic",
            modifier = Modifier
                .size(100.dp)
                .clickable {
                    if (permissionState.status.isGranted) {
                        speechRecogniserLauncher.launch(Unit)
                    } else
                        permissionState.launchPermissionRequest()
                },
            tint = colorResource(id = R.color.lavender)
        )
    }
}
