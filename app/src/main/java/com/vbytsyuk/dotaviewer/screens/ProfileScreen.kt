package com.vbytsyuk.dotaviewer.screens

import android.os.Bundle
import android.view.View
import com.vbytsyuk.dataprovider.ApiResult
import com.vbytsyuk.dataprovider.PlayerRepository
import com.vbytsyuk.dataprovider.SteamRepository
import com.vbytsyuk.domain.Player
import com.vbytsyuk.dotaviewer.R
import com.vbytsyuk.dotaviewer.mvp.BaseMvpFragment
import com.vbytsyuk.dotaviewer.mvp.BaseMvpPresenter
import com.vbytsyuk.dotaviewer.mvp.ViewState
import com.vbytsyuk.dotaviewer.widgets.StatsView
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.standalone.inject


typealias ProfileData = StatsView.Data


class ProfileFragment : BaseMvpFragment<ProfileData, ProfilePresenter>() {

    override val layoutId = R.layout.fragment_profile
    override val presenter: ProfilePresenter by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadUserInfo()
    }


    override fun renderData(data: ProfileData) {
        statsView.bind(data)
    }
}


class ProfilePresenter(
    override var viewState: ViewState<ProfileData> = ViewState.Loading
) : BaseMvpPresenter<ProfileData>(viewState) {

    private val steamRepository: SteamRepository by inject()
    private val playerRepository: PlayerRepository by inject()


    fun loadUserInfo() = CoroutineScope(Dispatchers.Main).launch {
        renderLoading()
        val result =
            withContext(Dispatchers.IO) { playerRepository.getPlayer(steamRepository.steamID) }
        when (result) {
            is ApiResult.Success -> updatePlayer(result.data)
            is ApiResult.Error -> renderError("Cant load player")
        }
    }

    private fun updatePlayer(player: Player) = renderData(
        ProfileData(
            avatarUrl = player.profile?.avatarMedium ?: "",
            name = player.profile?.personName ?: "",
            rank = player.mmrEstimate?.estimate.toString(),
            time = player.trackedUntil ?: "",
            winrate = player.rankTier.toString(),
            kda = player.leaderboardRank.toString()
        )
    )
}