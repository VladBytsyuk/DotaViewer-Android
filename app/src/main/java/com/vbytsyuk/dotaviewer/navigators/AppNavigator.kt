package com.vbytsyuk.dotaviewer.navigators

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.vbytsyuk.dotaviewer.AppScreen
import com.vbytsyuk.dotaviewer.R
import com.vbytsyuk.dotaviewer.name
import com.vbytsyuk.navigation.*


class AppNavigator : Navigator<AppScreen>(tabs = AppTab.values().toList()) {

    enum class AppTab : Tab { Profile, Pro, Settings }

    var fragmentManager: FragmentManager? = null

    override fun apply(command: NavigationCommand<AppScreen>) {
        when (command) {
            is ForwardCommand -> activeTabStack.push(command.destination as AppScreen)
            is BackCommand -> activeTabStack.pop()
            is ChangeTabCommand -> activeTab = command.tab
            else -> throw Navigator.UnsupportedCommandException("No such command")
        }

        val fragment = activeScreen as Fragment
        fragmentManager?.beginTransaction()?.run {
            replace(R.id.fragmentContainer, fragment, fragment.name)
            addToBackStack(null)
            commit()
        }

    }
}