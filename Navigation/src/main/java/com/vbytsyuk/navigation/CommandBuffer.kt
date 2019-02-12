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


    internal fun execute(command: NavigationCommand<Screen>) =
        navigator?.baseApply(command) ?: queue.add(command)
}