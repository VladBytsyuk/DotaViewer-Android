package com.vbytsyuk.dotaviewer

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.vbytsyuk.dotaviewer.navigators.FragmentNavigator
import com.vbytsyuk.dotaviewer.navigators.FragmentNavigator.AppTab
import com.vbytsyuk.navigation.Router
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : FragmentActivity() {

    private val navigator: FragmentNavigator by inject()
    private val router: Router<AppScreen> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { menuItem ->
            val selectedTab = when (menuItem.itemId) {
                R.id.tab_profile -> AppTab.Profile
                R.id.tab_pro -> AppTab.Pro
                R.id.tab_settings -> AppTab.Settings
                else -> return@setOnNavigationItemSelectedListener false
            }
            router.changeTab(selectedTab)
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onStart() {
        super.onStart()
        with(navigator) {
            fragmentManager = supportFragmentManager
            router.navigator = this
            initScreen()
        }
    }

    override fun onBackPressed() {
        if (navigator.activeTabStack.size > 1) {
            router.back()
        } else {
            finishAndRemoveTask()
        }
    }
}
