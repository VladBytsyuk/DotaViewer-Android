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

    public fun takeView(v: V) {
        view = v
        v.render(viewState)
    }

    public fun dropView(v: V) {
        if (view == v) {
            view = null
        }
    }

    public fun render() {
        view?.render(viewState)
    }
}
