package com.vbytsyuk.navigation

/**
 * Marker interface for entities that represents navigation tabs.
 */
interface Tab


/**
 * [Router] is an adapter between user calls and the platform’s navigation system,
 * implemented in [Navigator]
 */
open class Router<Screen> {
    private val commandBuffer: CommandBuffer<Screen> = CommandBuffer()


    /**
     * Platform-based implementation of [Navigator] interface
     */
    var navigator: Navigator<Screen>?
        get() = commandBuffer.navigator
        set(value) {
            commandBuffer.navigator = value
        }


    fun navigateTo(
        destination: Screen,
        data: String? = null
    ) = ForwardCommand(destination, data).execute()

    fun back() = BackCommand<Screen>().execute()

    fun changeTab(tab: Tab) = ChangeTabCommand<Screen>(tab).execute()


    private fun NavigationCommand<Screen>.execute() = commandBuffer.execute(this)
}
