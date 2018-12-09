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

    private var oldFragment: Fragment? = null
    private val activeFragment: Fragment get() = activeScreen


    override fun apply(command: NavigationCommand<AppScreen>) {
        replace(activeFragment, command.animation)
    }

    fun initScreen() = replace(activeFragment)


    private fun replace(newFragment: Fragment, animation: FragmentAnimation? = null) {
        fragmentManager?.replace(R.id.fragmentContainer, newFragment, oldFragment, animation)
        oldFragment = newFragment
    }


    private val NavigationCommand<AppScreen>.animation: FragmentAnimation?
        get() = when (this) {
            is ForwardCommand -> forwardCommandAnimation
            is BackCommand -> backCommandAnimation
            else -> null
        }

    private val forwardCommandAnimation: FragmentAnimation
        get() = FragmentAnimation(
            newScreenEnter = R.anim.enter_right,
            newScreenExit = R.anim.exit_left,
            oldScreenEnter = R.anim.exit_left,
            oldScreenExit = R.anim.exit_left
        )

    private val backCommandAnimation: FragmentAnimation
        get() = FragmentAnimation(
            newScreenEnter = R.anim.enter_left,
            newScreenExit = R.anim.exit_right,
            oldScreenEnter = R.anim.exit_right,
            oldScreenExit = R.anim.exit_right
        )
}