package com.example.dictionary.presenter

import com.example.dictionary.model.data.AppState
import com.example.dictionary.model.dataSource.DataSourceLocal
import com.example.dictionary.model.dataSource.DataSourceRemote
import com.example.dictionary.model.repository.RepoImpl
import com.example.dictionary.view.BaseView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenterImpl<T : AppState, V : BaseView>(
    private val interactor: MainInteractor = MainInteractor(
        RepoImpl(DataSourceRemote()),
        RepoImpl(DataSourceLocal())
    ),
    val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : BasePresenter<T, V> {
    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }

}

class SchedulerProvider {

    fun ui(): Scheduler = AndroidSchedulers.mainThread()

    fun io(): Scheduler = Schedulers.io()
}