package com.example.dictionary.model.dataSource

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}