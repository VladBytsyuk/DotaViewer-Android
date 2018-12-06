package com.vbytsyuk.navigation

import containers.AriadneQueue
import containers.Queue

internal class CommandBuffer<Screen> {
    private val queue: Queue<NavigationCommand<Screen>> = AriadneQueue()


    internal var navigator: Navigator<Screen>? = null
        internal set(new) {
            field = new
            field?.let { navigator ->
                while (queue.isNotEmpty()) navigator.baseApply(queue.poll())
            }
        }


    internal fun execute(command: NavigationCommand<Screen>) {
        val lockedNavigator: Navigator<Screen>? = navigator
        if (lockedNavigator != null) {
            lockedNavigator.baseApply(command)
        } else {
            queue.add(command)
        }
    }
}