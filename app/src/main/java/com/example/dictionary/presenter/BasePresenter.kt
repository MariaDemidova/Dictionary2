package com.example.dictionary.presenter

import com.example.dictionary.model.data.AppState
import com.example.dictionary.view.BaseView

interface BasePresenter<T: AppState, V: BaseView> {
    fun attachView(view: V)
    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}