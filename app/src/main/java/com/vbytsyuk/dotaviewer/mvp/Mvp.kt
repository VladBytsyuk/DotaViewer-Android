package com.vbytsyuk.dotaviewer.mvp

import android.arch.lifecycle.ViewModel

// Immutable
interface ViewState

interface View<in S : ViewState> {
    fun render(state: S)
}

abstract class Presenter<S : ViewState, V : View<S>>(protected var state: S) : ViewModel() {
    var views: MutableList<V> = ArrayList()

    fun takeView(view: V) {
        this.views.add(view)
        view.render(state)
    }

    fun takeView(views: List<V>) {
        this.views.addAll(views)
        views.forEach { it.render(state) }
    }

    fun dropViews() {
        this.views = ArrayList()
    }
}
