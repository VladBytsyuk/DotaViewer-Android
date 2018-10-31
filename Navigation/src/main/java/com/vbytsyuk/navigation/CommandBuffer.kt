package com.vbytsyuk.navigation

import java.util.*


internal class CommandBuffer<Screen> {
    private val queue: Queue<NavigationCommand<Screen>> = ArrayDeque()

    internal var navigator: Navigator<Screen>? = null
        internal set(new) {
            field = new
            field?.let { navigator ->
                while (queue.isNotEmpty()) navigator.apply(queue.poll())
            }
        }

    internal fun execute(command: NavigationCommand<Screen>) {
        val lockedNavigator: Navigator<Screen>? = navigator
        if (lockedNavigator != null) {
            lockedNavigator.apply(command)
        } else {
            queue.add(command)
        }
    }
}