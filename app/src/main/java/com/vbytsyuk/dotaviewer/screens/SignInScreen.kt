package com.vbytsyuk.dotaviewer.screens

import android.os.Bundle
import android.view.View
import com.vbytsyuk.dataprovider.SteamRepository
import com.vbytsyuk.dotaviewer.AppScreen
import com.vbytsyuk.dotaviewer.R
import com.vbytsyuk.dotaviewer.mvp.BaseMvpFragment
import com.vbytsyuk.dotaviewer.mvp.BaseMvpPresenter
import com.vbytsyuk.dotaviewer.mvp.ViewState
import com.vbytsyuk.navigation.Router
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.standalone.inject


interface ISignInEvents {
    fun done()
}


data class SignInData(
    val email: String = "",
    val password: String = "",
    val steamId: String = ""
)


class SignInFragment : BaseMvpFragment<SignInData, SignInPresenter>(), ISignInEvents {

    override val layoutId = R.layout.fragment_login
    override val presenter: SignInPresenter by viewModel()
    private val router: Router<AppScreen> by inject()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUp.setOnClickListener {
            presenter.signUp(login.text, password.text, steamId.text)
        }
    }

    override fun renderData(data: SignInData) {
        login.text = data.email
        password.text = data.password
        steamId.text = data.steamId
    }


    override fun done() = router.navigateTo(ProfileFragment())
}


class SignInPresenter(
    override var viewState: ViewState<SignInData> = ViewState.Loading
) : BaseMvpPresenter<SignInData>(viewState) {

    private val repository: SteamRepository by inject()

    private val signInEvents: ISignInEvents? get() = view as? ISignInEvents

    fun signUp(login: String, password: String, steamId: String) {
        repository.steamID = steamId
        renderData(SignInData(login, password, steamId))
        signInEvents?.done()
    }
}