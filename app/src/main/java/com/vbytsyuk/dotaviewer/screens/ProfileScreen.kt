package com.vbytsyuk.dotaviewer.screens

import android.os.Bundle
import android.view.View
import com.vbytsyuk.dotaviewer.R
import com.vbytsyuk.dotaviewer.widgets.StatsView
import com.vbytsyuk.mvp.BaseMvpFragment
import com.vbytsyuk.mvp.BaseMvpPresenter
import com.vbytsyuk.mvp.BaseMvpViewState
import com.vbytsyuk.mvp.IBaseMvpView
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel


private typealias Data = StatsView.Data
typealias ProfileViewState = BaseMvpViewState<Data>


interface IProfileView : IBaseMvpView<Data>

class ProfileFragment : BaseMvpFragment<Data, IProfileView, ProfilePresenter>(), IProfileView {
    override val layout = R.layout.fragment_profile
    override val presenter: ProfilePresenter by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statsView.setOnClickListener { presenter.loadUserInfo() }
    }


    override fun renderData(data: Data) {
        statsView.bind(data)
    }
}


class ProfilePresenter(
    override var viewState: ProfileViewState
) : BaseMvpPresenter<Data, IProfileView>(viewState) {
    fun loadUserInfo() {
        viewState = ProfileViewState(
            error = null,
            data = StatsView.Data(
                avatarUrl = "",
                name = "Alan Turing",
                rank = "Mathematician",
                time = "10000 hours",
                winrate = "69% winrate",
                kda = "KDA: 42"
            )
        )
        render()
    }
}