package com.example.speechtotextconverter.di

import com.example.speechtotextconverter.speechconverter.SpeechConverterViewModel
import org.koin.dsl.module

object ConfigurationClass {

    fun modules() = viewModelModule
}

val viewModelModule = module {
    single { SpeechConverterViewModel() }
}