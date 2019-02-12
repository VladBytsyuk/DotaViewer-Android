package com.vbytsyuk.dotaviewer.screens

import android.os.Bundle
import android.view.View
import com.vbytsyuk.dataprovider.SteamRepository
import com.vbytsyuk.dotaviewer.R
import com.vbytsyuk.dotaviewer.mvp.BaseMvpFragment
import com.vbytsyuk.dotaviewer.mvp.BaseMvpPresenter
import com.vbytsyuk.dotaviewer.mvp.ViewState
import com.vbytsyuk.dotaviewer.widgets.StatsView
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.standalone.inject


typealias ProViewState = ViewState<ProfileData>

class ProFragment : BaseMvpFragment<ProfileData, ProPresenter>() {
    override val layoutId = R.layout.fragment_profile
    override val presenter: ProPresenter by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statsView.setOnClickListener { presenter.loadUserInfo() }
    }


    override fun renderData(data: ProfileData) {
        statsView.bind(data)
    }
}


class ProPresenter(
    override var viewState: ViewState<ProfileData> = ViewState.Loading
) : BaseMvpPresenter<ProfileData>(viewState) {
    private val repository: SteamRepository by inject()

    fun loadUserInfo() {
        renderData(
            StatsView.Data(
                avatarUrl = "",
                name = "Albus Dumbledore",
                rank = repository.steamID,
                time = "10000 hours",
                winrate = "69% winrate",
                kda = "KDA: 42"
            )
        )
    }
}