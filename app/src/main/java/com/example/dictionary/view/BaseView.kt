package com.example.dictionary.view

import com.example.dictionary.model.data.AppState

interface BaseView {
    fun renderData(appState: AppState)

}