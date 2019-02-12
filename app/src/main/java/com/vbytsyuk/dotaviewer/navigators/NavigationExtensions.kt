package com.vbytsyuk.dotaviewer.navigators

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

enum class FragmentAnimation(val transition: Int) {
    Forward(FragmentTransaction.TRANSIT_FRAGMENT_OPEN),
    Back(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
}

internal fun FragmentManager.changeFragment(
    containerViewId: Int,
    newFragment: Fragment,
    animation: FragmentAnimation? = null
) = transaction {
    animation?.let { setTransition(animation.transition) }
    replaceWithTag(containerViewId, newFragment)
}

internal fun FragmentManager.transaction(
    action: FragmentTransaction.() -> FragmentTransaction
) = beginTransaction().disallowAddToBackStack().action().commit()

internal fun FragmentTransaction.replaceWithTag(
    containerViewId: Int,
    fragment: Fragment
) = replace(containerViewId, fragment, fragment.tag)