package com.vbytsyuk.dotaviewer

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.vbytsyuk.dotaviewer.navigators.ProfileTabNavigator
import com.vbytsyuk.dotaviewer.navigators.Screen
import com.vbytsyuk.dotaviewer.screens.SignInFragment
import com.vbytsyuk.navigation.Router
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : FragmentActivity() {
    private val navigator: ProfileTabNavigator by inject()
    private val router: Router<Screen> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { true }
    }

    override fun onStart() {
        super.onStart()
        navigator.fragmentManager = supportFragmentManager
        router.navigator = navigator
        router.navigateTo(SignInFragment())
    }
}
