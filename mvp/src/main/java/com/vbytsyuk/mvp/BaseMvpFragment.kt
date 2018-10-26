package com.vbytsyuk.mvp

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment


enum class BaseMvpStateEnum { Loading, Data, Error }
typealias State = BaseMvpStateEnum

open class BaseMvpViewState<D>(
    val error: String? = null,
    open val data: D? = null
) : IMvpViewState {
    val state: State
        get() = when {
            error != null -> BaseMvpStateEnum.Error
            data != null -> BaseMvpStateEnum.Data
            else -> BaseMvpStateEnum.Loading
        }
}


interface IBaseMvpView<D> : IMvpView<BaseMvpViewState<D>>

abstract class BaseMvpFragment<D, V : IBaseMvpView<D>, P : BaseMvpPresenter<D, V>> :
    Fragment(),
    IBaseMvpView<D> {

    abstract val presenter: P

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.takeView(this as V)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.dropView(this as V)
    }


    override fun render(viewState: BaseMvpViewState<D>) = when (viewState.state) {
        BaseMvpStateEnum.Loading -> renderLoading()
        BaseMvpStateEnum.Error -> renderError(viewState.error!!)
        BaseMvpStateEnum.Data -> renderData(viewState.data!!)
    }

    open fun renderLoading() {

    }

    open fun renderError(message: String) {
        AlertDialog.Builder(context)
            .setTitle("ERROR")
            .setMessage(message)
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }

    abstract fun renderData(data: D)
}


abstract class BaseMvpPresenter<D, V : IBaseMvpView<D>>(
    override var viewState: BaseMvpViewState<D>
) : MvpPresenter<BaseMvpViewState<D>, V>(viewState)