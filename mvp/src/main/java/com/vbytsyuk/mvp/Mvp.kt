package com.vbytsyuk.mvp

import androidx.lifecycle.ViewModel


interface IMvpViewState

interface IMvpView<in VS : IMvpViewState> {
    fun render(viewState: VS)
}


abstract class MvpPresenter<VS : IMvpViewState>(
    open var viewState: VS
) : ViewModel() {

    protected var view: IMvpView<VS>? = null

    public fun takeView(v: IMvpView<VS>) {
        view = v
        v.render(viewState)
    }

    public fun dropView() {
        view = null
    }

    public fun renderState(state: VS) {
        viewState = state
        view?.render(viewState)
    }
}
