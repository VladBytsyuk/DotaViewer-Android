package com.vbytsyuk.dotaviewer.navigators

import androidx.annotation.AnimRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


internal fun FragmentManager.changeFragment(
    containerViewId: Int,
    newFragment: Fragment,
    animation: FragmentAnimation? = null
) = transaction {
    animation?.let { setCustomAnimations(it) }
    replaceWithTag(containerViewId, newFragment)
}

internal fun FragmentManager.transaction(
    action: FragmentTransaction.() -> FragmentTransaction
) = beginTransaction().disallowAddToBackStack().action().commit()

internal fun FragmentTransaction.replaceWithTag(
    containerViewId: Int,
    fragment: Fragment
) = replace(containerViewId, fragment, fragment.tag)

internal fun FragmentTransaction.setCustomAnimations(
    animations: FragmentAnimation
) = setCustomAnimations(
    animations.newScreenEnter, animations.newScreenExit,
    animations.oldScreenEnter, animations.oldScreenExit
)


internal data class FragmentAnimation(
    @AnimRes val newScreenEnter: Int, @AnimRes val newScreenExit: Int,
    @AnimRes val oldScreenEnter: Int, @AnimRes val oldScreenExit: Int
)
