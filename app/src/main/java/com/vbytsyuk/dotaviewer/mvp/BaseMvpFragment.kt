package com.vbytsyuk.dotaviewer.mvp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vbytsyuk.mvp.IMvpView
import com.vbytsyuk.mvp.IMvpViewState
import com.vbytsyuk.mvp.MvpPresenter
import com.vbytsyuk.navigation.NavigationScreen
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

    fun copy(error: String? = this.error, data: D? = this.data) =
        BaseMvpViewState(error, data)
}


abstract class BaseMvpFragment<D, P : BaseMvpPresenter<D>> :
    Fragment(),
    IMvpView<BaseMvpViewState<D>>,
    NavigationScreen {

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
        presenter.takeView(this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onDestroyView() {
        view?.let { (it.parent as ViewGroup).removeAllViews() }
        super.onDestroyView()
        presenter.dropView(this)
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


abstract class BaseMvpPresenter<D>(
    override var viewState: BaseMvpViewState<D>
) : MvpPresenter<BaseMvpViewState<D>>(viewState), KoinComponent