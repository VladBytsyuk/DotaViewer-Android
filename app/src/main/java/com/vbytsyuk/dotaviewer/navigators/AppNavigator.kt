package com.vbytsyuk.dotaviewer.navigators

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.vbytsyuk.dotaviewer.AppScreen
import com.vbytsyuk.dotaviewer.R
import com.vbytsyuk.dotaviewer.replace
import com.vbytsyuk.navigation.NavigationCommand
import com.vbytsyuk.navigation.Navigator
import com.vbytsyuk.navigation.Tab


class AppNavigator(
    tabsToRoot: Map<AppTab, AppScreen>
) : Navigator<AppScreen>(tabsToRoot.map { (it.key as Tab) to it.value }.toMap()) {

    constructor(vararg tabsToRootPairs: Pair<AppTab, AppScreen>) : this(tabsToRootPairs.toMap())

    enum class AppTab : Tab { Profile, Pro, Settings }


    var fragmentManager: FragmentManager? = null

    override fun apply(command: NavigationCommand<AppScreen>) {
        val fragment = activeScreen as Fragment
        fragmentManager?.replace(R.id.fragmentContainer, fragment)
    }
}