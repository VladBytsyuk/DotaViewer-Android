package com.vbytsyuk.navigation

import java.util.*


internal class CommandBuffer {
    private val queue: Queue<NavigationCommand> = ArrayDeque()

    internal var navigator: Navigator? = null
        internal set(new) {
            field = new
            field?.let { navigator ->
                while (queue.isNotEmpty()) navigator.apply(queue.poll())
            }
        }

    internal fun execute(command: NavigationCommand) {
        val lockedNavigator: Navigator? = navigator
        if (lockedNavigator != null) {
            lockedNavigator.apply(command)
        } else {
            queue.add(command)
        }
    }
}