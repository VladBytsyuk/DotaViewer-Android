package com.vbytsyuk.mvp

import androidx.lifecycle.ViewModel

interface IMvpViewState

interface IMvpView<in VS : IMvpViewState> {
    fun render(viewState: VS)
}

abstract class MvpPresenter<VS : IMvpViewState, V : IMvpView<VS>>(
    open var viewState: VS
) : ViewModel() {

    private var view: V? = null

    fun takeView(v: V) {
        view = v
        v.render(viewState)
    }

    fun dropView(v: V) {
        if (view == v) {
            view = null
        }
    }
}
