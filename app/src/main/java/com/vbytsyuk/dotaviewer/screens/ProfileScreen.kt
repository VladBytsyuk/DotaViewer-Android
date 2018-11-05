package com.vbytsyuk.dotaviewer.screens

import android.os.Bundle
import android.view.View
import com.vbytsyuk.dataprovider.SteamRepository
import com.vbytsyuk.dotaviewer.*
import com.vbytsyuk.dotaviewer.widgets.StatsView
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.standalone.inject


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
    val repository: SteamRepository by inject()

    fun loadUserInfo() {
        viewState = ProfileViewState(
            error = null,
            data = StatsView.Data(
                avatarUrl = "",
                name = "Alan Turing",
                rank = repository.steamID,
                time = "10000 hours",
                winrate = "69% winrate",
                kda = "KDA: 42"
            )
        )
        render()
    }
}