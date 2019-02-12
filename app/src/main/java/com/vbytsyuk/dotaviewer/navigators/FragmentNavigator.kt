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
            is ForwardCommand -> forwardCommandAnimation
            is BackCommand -> backCommandAnimation
            else -> null
        }

    private val forwardCommandAnimation = FragmentAnimation(
        newScreenEnter = R.anim.enter_right, newScreenExit = R.anim.exit_left,
        oldScreenEnter = R.anim.enter_left, oldScreenExit = R.anim.exit_right
    )

    private val backCommandAnimation = FragmentAnimation(
        newScreenEnter = R.anim.enter_left, newScreenExit = R.anim.exit_right,
        oldScreenEnter = R.anim.enter_right, oldScreenExit = R.anim.exit_left
    )
}