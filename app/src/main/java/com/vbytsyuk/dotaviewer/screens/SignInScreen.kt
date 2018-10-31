package com.vbytsyuk.dotaviewer.screens

import android.os.Bundle
import android.view.View
import com.vbytsyuk.dotaviewer.R
import com.vbytsyuk.dotaviewer.Screen
import com.vbytsyuk.mvp.BaseMvpFragment
import com.vbytsyuk.mvp.BaseMvpPresenter
import com.vbytsyuk.mvp.BaseMvpViewState
import com.vbytsyuk.mvp.IBaseMvpView
import com.vbytsyuk.navigation.Router
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


data class SignInData(
    val steamLogin: String = "",
    val password: String = "",
    val loginDone: Boolean = false
)
typealias SignInViewState = BaseMvpViewState<SignInData>


interface ISignInView : IBaseMvpView<SignInData>

class SignInFragment : BaseMvpFragment<SignInData, ISignInView, SignInPresenter>(), ISignInView {
    override val layout = R.layout.fragment_login
    override val presenter: SignInPresenter by viewModel()
    private val router: Router<Screen> by inject()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUp.setOnClickListener {
            presenter.signUp(login.text.toString(), password.text.toString())
        }
    }


    override fun renderData(data: SignInData) {
        login.setText(data.steamLogin)
        password.setText(data.password)

        if (data.loginDone) router.navigateTo(ProfileFragment())
    }
}


class SignInPresenter(
    override var viewState: SignInViewState
) : BaseMvpPresenter<SignInData, ISignInView>(viewState) {
    fun signUp(login: String, password: String) {
        viewState = SignInViewState(
            error = viewState.error,
            data = SignInData(login, password, loginDone = true)
        )
        render()
    }
}