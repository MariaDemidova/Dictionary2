package com.example.dictionary.view


import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dictionary.model.data.AppState
import com.example.dictionary.presenter.BasePresenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), BaseView {

    lateinit var presenter: BasePresenter<T, BaseView>

    abstract fun createPresenter(): BasePresenter<T, BaseView>

   abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}