package com.example.dictionary.model.repository

import io.reactivex.Observable

interface BaseRepo<T> {
    fun getData(word: String): Observable<T>
}