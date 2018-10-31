package com.vbytsyuk.dotaviewer

import android.app.Application
import com.vbytsyuk.dotaviewer.screens.ProfilePresenter
import com.vbytsyuk.dotaviewer.screens.ProfileViewState
import com.vbytsyuk.dotaviewer.screens.SignInPresenter
import com.vbytsyuk.dotaviewer.screens.SignInViewState
import com.vbytsyuk.navigation.Router
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


class DotaViewerApp : Application() {
    private val koinModule: Module
        get() = module {
            single { ProfileTabNavigator() }
            single { Router<Screen>() }

            viewModel { SignInPresenter(SignInViewState()) }
            viewModel { ProfilePresenter(ProfileViewState()) }
        }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(koinModule))
    }

    companion object {
        lateinit var instance: DotaViewerApp
            private set
    }
}