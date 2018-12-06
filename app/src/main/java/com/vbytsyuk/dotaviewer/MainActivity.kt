package com.vbytsyuk.dotaviewer

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.vbytsyuk.dotaviewer.navigators.AppNavigator
import com.vbytsyuk.dotaviewer.screens.SignInFragment
import com.vbytsyuk.navigation.Router
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : FragmentActivity() {
    private val navigator: AppNavigator by inject()
    private val router: Router<AppScreen> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tab_profile -> router.changeTab(AppNavigator.AppTab.Profile)
                R.id.tab_pro -> router.changeTab(AppNavigator.AppTab.Pro)
                R.id.tab_settings -> router.changeTab(AppNavigator.AppTab.Settings)
                else -> return@setOnNavigationItemSelectedListener false
            }
            true
        }
    }

    override fun onStart() {
        super.onStart()
        navigator.fragmentManager = supportFragmentManager
        router.navigator = navigator
        router.navigateTo(SignInFragment())
    }

    override fun onBackPressed() {
        if (navigator.activeTabStack.size > 1) {
            router.back()
        } else {
            super.onBackPressed()
        }
    }
}
