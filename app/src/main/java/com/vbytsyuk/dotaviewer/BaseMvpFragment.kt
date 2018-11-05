package com.vbytsyuk.dotaviewer

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vbytsyuk.mvp.IMvpView
import com.vbytsyuk.mvp.IMvpViewState
import com.vbytsyuk.mvp.MvpPresenter
import org.koin.standalone.KoinComponent


enum class BaseMvpStateEnum { Loading, Data, Error }

open class BaseMvpViewState<D>(
    val error: String? = null,
    open val data: D? = null
) : IMvpViewState {
    val state: BaseMvpStateEnum
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

    abstract val layout: Int
    abstract val presenter: P


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layout, container, false)

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.takeView(this as V)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onDestroyView() {
        view?.let { (it.parent as ViewGroup).removeAllViews() }
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
) : MvpPresenter<BaseMvpViewState<D>, V>(viewState), KoinComponent