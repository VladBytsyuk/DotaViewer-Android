package com.vbytsyuk.dotaviewer

import android.support.v4.app.Fragment
import com.vbytsyuk.dotaviewer.mvp.Presenter
import com.vbytsyuk.dotaviewer.mvp.View
import com.vbytsyuk.dotaviewer.mvp.ViewState
import com.vbytsyuk.dotaviewer.widgets.StatsView

enum class StateEnum { Progress, Loaded, Error }

val EMPTY_DATA_ERROR = R.string.error_text_empty_data.resString

data class ProfileViewState(
    val state: StateEnum,
    val header: StatsView.Data?,
    val error: String
) : ViewState

class ProfileFragment : Fragment(), View<ProfileViewState> {
    override fun render(state: ProfileViewState) {
        when (state.state) {
            StateEnum.Progress -> renderProgress()
            StateEnum.Error -> renderError(state.error)
            StateEnum.Loaded ->
                if (state.header == null) renderError(EMPTY_DATA_ERROR) else renderLoaded(state.header)
        }
    }

    private fun renderProgress() {

    }

    private fun renderLoaded(header: StatsView.Data) {

    }

    private fun renderError(error: String) {

    }
}

class ProfilePresenter :
    Presenter<ProfileViewState, ProfileFragment>(
        ProfileViewState(
            StateEnum.Progress,
            null,
            ""
        )
    ) {

}