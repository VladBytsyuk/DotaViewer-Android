package com.vbytsyuk.dotaviewer

import android.app.Application
import com.vbytsyuk.dataprovider.SteamRepository
import com.vbytsyuk.dotaviewer.navigators.ProfileTabNavigator
import com.vbytsyuk.dotaviewer.screens.ProfilePresenter
import com.vbytsyuk.dotaviewer.screens.ProfileViewState
import com.vbytsyuk.dotaviewer.screens.SignInPresenter
import com.vbytsyuk.dotaviewer.screens.SignInViewState
import com.vbytsyuk.dotaviewer.shared_preferences.SharedPreferencesSource
import com.vbytsyuk.navigation.Router
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


class DotaViewerApp : Application() {
    private val navigationKoinModule: Module
        get() = module {
            single { ProfileTabNavigator() }
            single { Router() }
        }

    private val mvpKoinModule: Module
        get() = module {
            viewModel { SignInPresenter(SignInViewState()) }
            viewModel { ProfilePresenter(ProfileViewState()) }
        }
    private val dataKoinModule: Module
        get() = module {
            single { SteamRepository(SharedPreferencesSource()) }
        }


    override fun onCreate() {
        super.onCreate()
        instance = this
        val koinModulesList = listOf(navigationKoinModule, mvpKoinModule, dataKoinModule)
        startKoin(this, koinModulesList)
    }

    companion object {
        lateinit var instance: DotaViewerApp
            private set
    }
}