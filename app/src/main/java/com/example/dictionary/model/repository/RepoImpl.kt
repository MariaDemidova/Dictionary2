package com.example.dictionary.model.repository

import com.example.dictionary.model.data.DataModel
import com.example.dictionary.model.dataSource.DataSource
import io.reactivex.Observable

class RepoImpl(private val dataSource: DataSource<List<DataModel>>): BaseRepo<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}