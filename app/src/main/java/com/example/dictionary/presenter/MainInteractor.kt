package com.example.dictionary.presenter

import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.data.DataModel
import com.example.dictionary.model.repository.BaseRepo
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: BaseRepo<List<DataModel>>,
    private val localRepository: BaseRepo<List<DataModel>>
): Interactor<AppState> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}