package com.vbytsyuk.dotaviewer

import android.app.Application
import com.vbytsyuk.dotaviewer.profile.ProfilePresenter
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

class DotaViewerApp : Application() {
    val koinModule: Module
        get() = module {
            viewModel { ProfilePresenter(get()) }
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