package com.vbytsyuk.dotaviewer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.vbytsyuk.mvp.BaseMvpFragment
import com.vbytsyuk.navigation.BackCommand
import com.vbytsyuk.navigation.ForwardCommand
import com.vbytsyuk.navigation.NavigationCommand
import com.vbytsyuk.navigation.Navigator


typealias Screen = BaseMvpFragment<*, *, *>

class ProfileTabNavigator : Navigator<Screen> {
    var fragmentManager: FragmentManager? = null

    override fun apply(command: NavigationCommand<Screen>) {
        when (command) {
            is ForwardCommand -> {
                val fragment = command.destination as Fragment
                fragmentManager?.beginTransaction()?.run {
                    replace(R.id.fragmentContainer, fragment, fragment.javaClass.canonicalName)
                    addToBackStack(null)
                    commit()
                }
            }

            is BackCommand -> fragmentManager?.popBackStack()

            else -> throw Navigator.UnsupportedCommandException(
                "Command $command doesn't supported by ${this.javaClass.canonicalName}"
            )
        }
    }
}