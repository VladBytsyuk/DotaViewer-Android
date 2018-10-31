package com.vbytsyuk.navigation

import java.util.*


internal typealias ScreenWithData<Screen> = Pair<Screen, String?>

/**
 * [Router] is an adapter between user calls and the platform’s navigation system,
 * implemented in [Navigator]
 * [Screen] is platform's type that represents application screen
 */
public open class Router<Screen> {
    private val screenStack: Stack<ScreenWithData<Screen>> = Stack()
    private val commandBuffer: CommandBuffer<Screen> = CommandBuffer()

    /**
     * Platform-based implementation of [Navigator] interface
     */
    public var navigator: Navigator<Screen>? = null
        public set(new) {
            commandBuffer.navigator = new
        }


    /**
     * Wrapper for [ForwardCommand]
     */
    public fun navigateTo(
        destination: Screen,
        data: String? = null
    ) {
        screenStack.push(destination to data)
        commandBuffer.execute(ForwardCommand(destination, data))
    }

    /**
     * Wrapper for [BackCommand]
     */
    public fun back() {
        screenStack.pop()
        commandBuffer.execute(BackCommand())
    }
}
