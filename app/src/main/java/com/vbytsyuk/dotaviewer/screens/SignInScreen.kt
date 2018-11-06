package com.vbytsyuk.dotaviewer.screens

import android.os.Bundle
import android.view.View
import com.vbytsyuk.dataprovider.SteamRepository
import com.vbytsyuk.dotaviewer.*
import com.vbytsyuk.dotaviewer.navigators.Screen
import com.vbytsyuk.navigation.Router
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.standalone.inject


data class SignInData(
    val email: String = "",
    val password: String = "",
    val steamId: String = "",
    val isSignInDone: Boolean = false
)
typealias SignInViewState = BaseMvpViewState<SignInData>


class SignInFragment : BaseMvpFragment<SignInData, SignInPresenter>() {
    override val layout = R.layout.fragment_login
    override val presenter: SignInPresenter by viewModel()
    private val router: Router<Screen> by inject()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUp.setOnClickListener {
            presenter.signUp(login.inputText, password.inputText, steamId.inputText)
        }
    }


    override fun renderData(data: SignInData) {
        login.setText(data.email)
        password.setText(data.password)
        steamId.setText(data.steamId)

        if (data.isSignInDone) {
            router.navigateTo(ProfileFragment())
            presenter.clearSignInDone()
        }
    }
}


class SignInPresenter(
    override var viewState: SignInViewState
) : BaseMvpPresenter<SignInData>(viewState) {
    private val repository: SteamRepository by inject()


    fun signUp(login: String, password: String, steamId: String) {
        repository.steamID = steamId
        viewState = viewState.copy(data = SignInData(login, password, steamId, isSignInDone = true))
        render()
    }


    fun clearSignInDone() {
        viewState = viewState.copy(data = viewState.data?.copy(isSignInDone = false))
    }
}