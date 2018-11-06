package com.vbytsyuk.navigation

import sun.awt.X11.Screen
import java.util.*


public interface NavigationScreen

internal typealias ScreenWithData = Pair<NavigationScreen, String?>

/**
 * [Router] is an adapter between user calls and the platform’s navigation system,
 * implemented in [Navigator]
 * [Screen] is platform's type that represents application screen
 */
public open class Router {
    private val screenStack: Stack<ScreenWithData> = Stack()
    private val commandBuffer: CommandBuffer = CommandBuffer()

    /**
     * Platform-based implementation of [Navigator] interface
     */
    public var navigator: Navigator?
        public set(new) {
            commandBuffer.navigator = new
        }
        public get() = commandBuffer.navigator


    /**
     * Wrapper for [ForwardCommand]
     */
    public fun navigateTo(
        destination: NavigationScreen,
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
