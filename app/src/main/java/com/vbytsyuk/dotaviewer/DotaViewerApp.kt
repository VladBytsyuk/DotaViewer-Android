package com.vbytsyuk.dotaviewer

import android.app.Application
import com.vbytsyuk.dataprovider.SteamRepository
import com.vbytsyuk.dotaviewer.mvp.BaseMvpFragment
import com.vbytsyuk.dotaviewer.navigators.AppNavigator
import com.vbytsyuk.dotaviewer.screens.*
import com.vbytsyuk.dotaviewer.shared_preferences.SharedPreferencesSource
import com.vbytsyuk.navigation.Router
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

typealias AppScreen = BaseMvpFragment<*, *>

class DotaViewerApp : Application() {
    private val navigationKoinModule: Module
        get() = module {
            single {
                AppNavigator(
                    AppNavigator.AppTab.Profile to SignInFragment(),
                    AppNavigator.AppTab.Pro to ProFragment(),
                    AppNavigator.AppTab.Settings to SignInFragment()
                )
            }
            single { Router<AppScreen>() }
        }

    private val mvpKoinModule: Module
        get() = module {
            viewModel { SignInPresenter(SignInViewState()) }
            viewModel { ProfilePresenter(ProfileViewState()) }
            viewModel { ProPresenter(ProViewState()) }
        }

    private val dataKoinModule: Module
        get() = module {
            single { SteamRepository(SharedPreferencesSource()) }
        }


    override fun onCreate() {
        super.onCreate()
        instance = this
        val koinModulesList = listOf(
            navigationKoinModule,
            mvpKoinModule,
            dataKoinModule
        )
        startKoin(this, koinModulesList)
    }

    companion object {
        lateinit var instance: DotaViewerApp
            private set
    }
}