package com.vbytsyuk.dotaviewer.navigators

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.vbytsyuk.dotaviewer.AppScreen
import com.vbytsyuk.dotaviewer.R
import com.vbytsyuk.navigation.*


class FragmentNavigator(
    tabsToRoot: Map<AppTab, AppScreen>
) : Navigator<AppScreen>(tabsToRoot.map { (it.key as Tab) to it.value }.toMap()) {

    constructor(vararg tabsToRootPairs: Pair<AppTab, AppScreen>) : this(tabsToRootPairs.toMap())

    enum class AppTab : Tab { Profile, Pro, Settings }

    var fragmentManager: FragmentManager? = null

    private val activeFragment: Fragment get() = activeScreen


    override fun apply(command: NavigationCommand<AppScreen>) {
        changeFragment(activeFragment, command.animation)
    }

    fun initScreen() = changeFragment(activeFragment)


    private fun changeFragment(newFragment: Fragment, animation: FragmentAnimation? = null) =
        fragmentManager?.changeFragment(R.id.fragmentContainer, newFragment, animation)


    private val NavigationCommand<AppScreen>.animation: FragmentAnimation?
        get() = when (this) {
            is ForwardCommand -> FragmentAnimation.Forward
            is BackCommand -> FragmentAnimation.Back
            else -> null
        }
}