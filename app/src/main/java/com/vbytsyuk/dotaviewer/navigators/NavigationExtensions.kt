package com.vbytsyuk.dotaviewer.navigators

import androidx.annotation.AnimRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.vbytsyuk.dotaviewer.snapshot


internal fun FragmentManager.replace(
    containerViewId: Int,
    fragment: Fragment,
    oldFragment: Fragment? = null,
    animationIds: FragmentAnimation? = null
) = transaction {
    animationIds?.let {
        setCustomAnimations(
            it.newScreenEnter,
            it.newScreenExit,
            it.oldScreenEnter,
            it.oldScreenExit
        )
    }
    oldFragment?.let { old ->
        fragments.snapshot.filter { it != old }.forEach { remove(it) }
    }
    replaceWithTag(containerViewId, fragment, oldFragment, !fragments.contains(fragment))
}

internal fun FragmentManager.transaction(
    action: FragmentTransaction.() -> FragmentTransaction
) = beginTransaction().action().commit()


internal fun FragmentTransaction.replaceWithTag(
    containerViewId: Int,
    newFragment: Fragment,
    oldFragment: Fragment? = null,
    isSafe: Boolean = false
): FragmentTransaction {
    if (oldFragment != null && isSafe) {
        hide(oldFragment)
        return add(containerViewId, newFragment, newFragment.tag)
    } else {
        return replace(containerViewId, newFragment, newFragment.tag)
    }
}


data class FragmentAnimation(
    @AnimRes val newScreenEnter: Int, @AnimRes val newScreenExit: Int,
    @AnimRes val oldScreenEnter: Int, @AnimRes val oldScreenExit: Int
)
