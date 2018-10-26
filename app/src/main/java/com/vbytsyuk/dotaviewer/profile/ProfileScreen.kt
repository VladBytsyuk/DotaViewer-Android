package com.vbytsyuk.dotaviewer.profile

import com.vbytsyuk.dotaviewer.widgets.StatsView
import com.vbytsyuk.mvp.BaseMvpFragment
import com.vbytsyuk.mvp.BaseMvpPresenter
import com.vbytsyuk.mvp.BaseMvpViewState
import com.vbytsyuk.mvp.IBaseMvpView
import org.koin.androidx.viewmodel.ext.android.viewModel


private typealias Data = StatsView.Data
private typealias ProfileViewState = BaseMvpViewState<Data>

interface IProfileView : IBaseMvpView<Data>

class ProfileFragment : BaseMvpFragment<Data, IProfileView, ProfilePresenter>(), IProfileView {
    override val presenter: ProfilePresenter by viewModel()

    override fun renderData(data: Data) {
    }
}

class ProfilePresenter(
    override var viewState: ProfileViewState
) : BaseMvpPresenter<Data, IProfileView>(viewState)