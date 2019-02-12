package com.vbytsyuk.dotaviewer.mvp

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.vbytsyuk.dotaviewer.AutoInflateFragment
import com.vbytsyuk.mvp.IMvpView
import com.vbytsyuk.mvp.IMvpViewState
import com.vbytsyuk.mvp.MvpPresenter
import org.koin.standalone.KoinComponent


sealed class ViewState<out D> : IMvpViewState {
    data class Data<out D>(val data: D) : ViewState<D>()
    data class Error(val message: String) : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
}


abstract class BaseMvpFragment<D, P : BaseMvpPresenter<D>> :
    AutoInflateFragment(),
    IMvpView<ViewState<D>> {

    abstract val presenter: P


    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.takeView(this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onDestroyView() {
        view?.parent?.let { (it as ViewGroup).removeAllViews() }
        super.onDestroyView()
        presenter.dropView()
    }


    override fun render(viewState: ViewState<D>) = when (viewState) {
        is ViewState.Error -> renderError(viewState.message)
        is ViewState.Data -> renderData(viewState.data)
        is ViewState.Loading -> renderLoading()
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


abstract class BaseMvpPresenter<D>(
    override var viewState: ViewState<D> = ViewState.Loading
) : MvpPresenter<ViewState<D>>(viewState), KoinComponent {
    fun renderLoading() = renderState(ViewState.Loading)

    fun renderError(message: String) = renderState(ViewState.Error(message))

    fun renderData(data: D) = renderState(ViewState.Data(data))
}