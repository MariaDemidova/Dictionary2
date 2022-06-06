package com.example.dictionary.model.dataSource

import com.example.dictionary.model.data.DataModel
import io.reactivex.Observable

class RoomDataBaseImpl : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO()
    }
}
